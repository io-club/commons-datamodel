/*
 * Copyright 2025 IO Club
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fyi.ioclub.commons.datamodel.collection.qmparted

// Iterators

interface SubtypeIteratorMod {

    fun remove()

    interface DelegateLesser {

        fun remove(): Unit = modificationUnsupported()

        class Unsupported : DelegateLesser
    }
}

fun modDelegateTo(lesser: SubtypeIteratorMod.DelegateLesser): SubtypeIteratorMod =
    object : SubtypeIteratorMod, SubtypeIteratorMod.DelegateLesser by lesser {}

interface IteratorMod : SubtypeIteratorMod {

    interface DelegateLesser : SubtypeIteratorMod.DelegateLesser {

        class Unsupported : DelegateLesser
    }
}

fun modDelegateTo(lesser: IteratorMod.DelegateLesser): IteratorMod =
    object : IteratorMod, IteratorMod.DelegateLesser by lesser {}

fun <E> modDelegateTo(delegate: MutableIterator<E>): IteratorMod =
    object : IteratorMod, MutableIterator<E> by delegate {}

interface SubtypeListIteratorMod<in E> : SubtypeIteratorMod, SubtypeListIteratorInMod<E> {

    interface DelegateLesser<in E> : SubtypeIteratorMod.DelegateLesser, SubtypeListIteratorInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> modDelegateTo(lesser: SubtypeListIteratorMod.DelegateLesser<E>): SubtypeListIteratorMod<E> =
    object : SubtypeListIteratorMod<E>, SubtypeListIteratorMod.DelegateLesser<E> by lesser {}

interface ListIteratorMod<in E> : SubtypeListIteratorMod<E>, ListIteratorInMod<E> {

    interface DelegateLesser<in E> : SubtypeListIteratorMod.DelegateLesser<E>, ListIteratorInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> modDelegateTo(lesser: ListIteratorMod.DelegateLesser<E>): ListIteratorMod<E> =
    object : ListIteratorMod<E>, ListIteratorMod.DelegateLesser<E> by lesser {}

fun <E> modDelegateTo(delegate: MutableListIterator<E>): ListIteratorMod<E> =
    object : ListIteratorMod<E>, MutableListIterator<E> by delegate {}

// Collections

interface SubtypeCollectionMod<in E> : SubtypeCollectionInMod<E> {

    // Query Operations

    fun iteratorMod(): IteratorMod

    // Bulk Modification Operations

    fun clear()

    interface DelegateLesser<E> : SubtypeCollectionInMod.DelegateLesser<E> {

        fun iterator(): MutableIterator<E> = unsupported()

        fun remove(): Unit = modificationUnsupported()

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> modDelegateTo(lesser: SubtypeCollectionMod.DelegateLesser<E>): SubtypeCollectionMod<E> =
    object : SubtypeCollectionMod<E>,
        SubtypeCollectionInMod<E> by inModDelegateTo(lesser as SubtypeCollectionInMod.DelegateLesser<E>) {
        override fun iteratorMod() = modDelegateTo(lesser.iterator())
        override fun clear() {
            val it = lesser.iterator()
            while (it.hasNext()) {
                it.next()
                it.remove()
            }
        }
    }

interface CollectionMod<in E> : SubtypeCollectionMod<E>, CollectionInMod<E> {

    interface DelegateLesser<E> : SubtypeCollectionMod.DelegateLesser<E>, CollectionInMod.DelegateLesser<E> {

        override fun iterator(): MutableIterator<E> = super<SubtypeCollectionMod.DelegateLesser>.iterator()

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> modDelegateTo(lesser: CollectionMod.DelegateLesser<E>): CollectionMod<E> =
    object : CollectionMod<E>,
        CollectionInMod<E> by inModDelegateTo(lesser) {
        private val modDelegate = modDelegateTo(lesser as SubtypeCollectionMod.DelegateLesser<E>)
        override fun iteratorMod() = modDelegate.iteratorMod()
        override fun clear() = modDelegate.clear()
    }

fun <E> modDelegateTo(delegate: MutableCollection<E>): CollectionMod<E> =
    object : CollectionMod<E>, MutableCollection<E> by delegate {
        override fun iteratorMod() = modDelegateTo(delegate.iterator())
    }

interface SubtypeListMod<E> : SubtypeCollectionMod<E>, SubtypeListInMod<E> {

    // Positional Access Operations

    fun removeAt(index: Int): E

    // List Iterators

    fun listIteratorMod(): ListIteratorMod<E>

    fun listIteratorMod(index: Int): ListIteratorMod<E>

    // View

    fun subListMod(fromIndex: Int, toIndex: Int): ListMod<E>

    interface DelegateLesser<E> : SubtypeCollectionMod.DelegateLesser<E>, SubtypeListInMod.DelegateLesser<E> {

        fun removeAt(index: Int): E = modificationUnsupported()

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> modDelegateTo(lesser: SubtypeListMod.DelegateLesser<E>): SubtypeListMod<E> =
    object : SubtypeListMod<E>, SubtypeListInMod<E> by inModDelegateTo(lesser) {
        private val modDelegate = modDelegateTo(lesser as SubtypeCollectionMod.DelegateLesser<E>)
        override fun removeAt(index: Int) = lesser.removeAt(index)
        override fun listIteratorMod() = modDelegateTo(lesser.listIterator())
        override fun listIteratorMod(index: Int) = modDelegateTo(lesser.listIterator(index))
        override fun subListMod(fromIndex: Int, toIndex: Int) = modDelegateTo(lesser.subList(fromIndex, toIndex))
        override fun iteratorMod() = modDelegate.iteratorMod()
        override fun clear() = modDelegate.clear()
    }

interface ListMod<E> : CollectionMod<E>, SubtypeListMod<E>, ListInMod<E> {

    interface DelegateLesser<E> : CollectionMod.DelegateLesser<E>, SubtypeListMod.DelegateLesser<E>,
        ListInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> modDelegateTo(lesser: ListMod.DelegateLesser<E>): ListMod<E> =
    object : ListMod<E>, SubtypeListMod<E> by modDelegateTo(lesser) {
        private val inModDelegate = inModDelegateTo(lesser)
        override fun remove(element: E) = inModDelegate.remove(element)
        override fun removeAll(elements: Collection<E>) = inModDelegate.removeAll(elements)
        override fun retainAll(elements: Collection<E>) = inModDelegate.retainAll(elements)
    }

fun <E> modDelegateTo(delegate: MutableList<E>): ListMod<E> =
    object : ListMod<E>, ListInMod<E> by inModDelegateTo(delegate) {
        override fun iteratorMod() = modDelegateTo(delegate.iterator())
        override fun clear() = delegate.clear()
        override fun removeAt(index: Int) = delegate.removeAt(index)
        override fun listIteratorMod() = modDelegateTo(delegate.listIterator())
        override fun listIteratorMod(index: Int) = modDelegateTo(delegate.listIterator(index))
        override fun subListMod(fromIndex: Int, toIndex: Int) = modDelegateTo(delegate.subList(fromIndex, toIndex))
    }

interface SubtypeSetMod<in E> : SubtypeCollectionMod<E>, SubtypeSetInMod<E> {

    interface DelegateLesser<E> : SubtypeCollectionMod.DelegateLesser<E>, SubtypeSetInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> modDelegateTo(lesser: SubtypeSetMod.DelegateLesser<E>): SubtypeSetMod<E> =
    object : SubtypeSetMod<E>,
        SubtypeCollectionMod<E> by modDelegateTo(lesser as SubtypeCollectionMod.DelegateLesser<E>) {}

interface SetMod<in E> : CollectionMod<E>, SubtypeSetMod<E>, SetInMod<E> {

    interface DelegateLesser<E> : CollectionMod.DelegateLesser<E>, SubtypeSetMod.DelegateLesser<E>,
        SetInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> modDelegateTo(lesser: SetMod.DelegateLesser<E>): SetMod<E> =
    object : SetMod<E>, CollectionMod<E> by modDelegateTo(lesser as CollectionMod.DelegateLesser<E>) {}

fun <E> modDelegateTo(delegate: MutableSet<E>): SetMod<E> = object : SetMod<E>, MutableSet<E> by delegate {
    override fun iteratorMod() = modDelegateTo(delegate.iterator())
}

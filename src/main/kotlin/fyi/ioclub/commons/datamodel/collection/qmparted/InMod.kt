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

interface SubtypeListIteratorInMod<in E> {

    fun add(element: E)

    fun set(element: E)

    interface DelegateLesser<in E> {

        fun add(element: E): Unit = modificationUnsupported()

        fun set(element: E): Unit = modificationUnsupported()

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> inModDelegateTo(lesser: SubtypeListIteratorInMod.DelegateLesser<E>): SubtypeListIteratorInMod<E> =
    object : SubtypeListIteratorInMod<E>, SubtypeListIteratorInMod.DelegateLesser<E> by lesser {}

interface ListIteratorInMod<in E> : SubtypeListIteratorInMod<E> {

    interface DelegateLesser<in E> : SubtypeListIteratorInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> inModDelegateTo(lesser: ListIteratorInMod.DelegateLesser<E>): ListIteratorInMod<E> =
    object : ListIteratorInMod<E>, ListIteratorInMod.DelegateLesser<E> by lesser {}

fun <E> inModDelegateTo(delegate: MutableListIterator<E>): ListIteratorInMod<E> =
    object : ListIteratorInMod<E>, MutableListIterator<E> by delegate {}

// Collections

interface SubtypeCollectionInMod<in E> {

    // Modification Operations

    fun add(element: E): Boolean

    // Bulk Modification Operations

    fun addAll(elements: Collection<E>): Boolean

    interface DelegateLesser<in E> {

        fun add(element: E): Boolean = modificationUnsupported()

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> inModDelegateTo(lesser: SubtypeCollectionInMod.DelegateLesser<E>): SubtypeCollectionInMod<E> =
    object : SubtypeCollectionInMod<E>, SubtypeCollectionInMod.DelegateLesser<E> by lesser {

        override fun addAll(elements: Collection<E>): Boolean {
            var modified = false
            elements.forEach { if (add(it)) modified = true }
            return modified
        }
    }

interface CollectionInMod<in E> : SubtypeCollectionInMod<E> {

    // Modification Operations

    fun remove(element: E): Boolean

    // Bulk Modification Operations

    fun removeAll(elements: Collection<E>): Boolean

    fun retainAll(elements: Collection<E>): Boolean

    interface DelegateLesser<E> : SubtypeCollectionInMod.DelegateLesser<E> {

        fun iterator(): MutableIterator<E> = unsupported()

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> inModDelegateTo(lesser: CollectionInMod.DelegateLesser<E>): CollectionInMod<E> =
    object : CollectionInMod<E>,
        SubtypeCollectionInMod<E> by inModDelegateTo(lesser as SubtypeCollectionInMod.DelegateLesser<E>) {

        override fun remove(element: E): Boolean {
            val it = lesser.iterator()
            if (element == null) {
                while (it.hasNext()) {
                    if (it.next() == null) {
                        it.remove()
                        return true
                    }
                }
            } else {
                while (it.hasNext()) {
                    if (element == it.next()) {
                        it.remove()
                        return true
                    }
                }
            }
            return false
        }

        // Bulk Modification Operations

        override fun removeAll(elements: Collection<E>): Boolean {
            var modified = false
            val it = lesser.iterator()
            while (it.hasNext()) {
                if (it.next() in elements) {
                    it.remove()
                    modified = true
                }
            }
            return modified
        }

        override fun retainAll(elements: Collection<E>): Boolean {
            var modified = false
            val it = lesser.iterator()
            while (it.hasNext()) {
                if (it.next() !in elements) {
                    it.remove()
                    modified = true
                }
            }
            return modified
        }
    }

fun <E> inModDelegateTo(delegate: MutableCollection<E>): CollectionInMod<E> =
    object : CollectionInMod<E>, MutableCollection<E> by delegate {}

interface SubtypeListInMod<E> : SubtypeCollectionInMod<E> {

    // Bulk Modification Operations

    fun addAll(index: Int, elements: Collection<E>): Boolean

    // Positional Access Operations

    fun set(index: Int, element: E): E

    fun add(index: Int, element: E)

    // List Iterators

    fun listIteratorInMod(): ListIteratorInMod<E>

    fun listIteratorInMod(index: Int): ListIteratorInMod<E>

    // View

    fun subListInMod(fromIndex: Int, toIndex: Int): ListInMod<E>

    interface DelegateLesser<E> : SubtypeCollectionInMod.DelegateLesser<E> {

        val size: Int get() = unsupported()

        operator fun set(index: Int, element: E): E = modificationUnsupported()

        fun add(index: Int, element: E): Unit = modificationUnsupported()

        fun listIterator(): MutableListIterator<E> = unsupported()

        fun listIterator(index: Int): MutableListIterator<E> = unsupported()

        fun subList(fromIndex: Int, toIndex: Int): MutableList<E> = unsupported()

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> inModDelegateTo(lesser: SubtypeListInMod.DelegateLesser<E>): SubtypeListInMod<E> =
    object : SubtypeListInMod<E>,
        SubtypeCollectionInMod<E> by inModDelegateTo(lesser as SubtypeCollectionInMod.DelegateLesser<E>) {
        override fun addAll(index: Int, elements: Collection<E>): Boolean {
            rangeCheckForAdd(index)
            var i = index
            var modified = false
            elements.forEach {
                add(i++, it)
                modified = true
            }
            return modified
        }

        private fun rangeCheckForAdd(index: Int) =
            if (index in 0..lesser.size) throw IndexOutOfBoundsException("Index: $index, Size: $lesser.size") else Unit

        override operator fun set(index: Int, element: E) = lesser.set(index, element)
        override fun add(index: Int, element: E) = lesser.add(index, element)
        override fun listIteratorInMod() = inModDelegateTo(lesser.listIterator())
        override fun listIteratorInMod(index: Int) = inModDelegateTo(lesser.listIterator(index))
        override fun subListInMod(fromIndex: Int, toIndex: Int) = inModDelegateTo(lesser.subList(fromIndex, toIndex))
    }

interface ListInMod<E> : CollectionInMod<E>, SubtypeListInMod<E> {

    interface DelegateLesser<E> : CollectionInMod.DelegateLesser<E>, SubtypeListInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> inModDelegateTo(lesser: ListInMod.DelegateLesser<E>): ListInMod<E> =
    object : ListInMod<E>, SubtypeListInMod<E> by inModDelegateTo(lesser as SubtypeListInMod.DelegateLesser<E>) {
        private val collectionDelegate = inModDelegateTo(lesser as CollectionInMod.DelegateLesser<E>)
        override fun remove(element: E) = collectionDelegate.remove(element)
        override fun removeAll(elements: Collection<E>) = collectionDelegate.removeAll(elements)
        override fun retainAll(elements: Collection<E>) = collectionDelegate.retainAll(elements)
    }

fun <E> inModDelegateTo(delegate: MutableList<E>): ListInMod<E> =
    object : ListInMod<E>, MutableList<E> by delegate {
        override fun listIteratorInMod() = inModDelegateTo(delegate.listIterator())
        override fun listIteratorInMod(index: Int) = inModDelegateTo(delegate.listIterator(index))
        override fun subListInMod(fromIndex: Int, toIndex: Int) = inModDelegateTo(delegate.subList(fromIndex, toIndex))
    }

interface SubtypeSetInMod<in E> : SubtypeCollectionInMod<E> {

    interface DelegateLesser<in E> : SubtypeCollectionInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> inModDelegateTo(lesser: SubtypeSetInMod.DelegateLesser<E>): SubtypeSetInMod<E> =
    object : SubtypeSetInMod<E>,
        SubtypeCollectionInMod<E> by inModDelegateTo(lesser as SubtypeCollectionInMod.DelegateLesser<E>) {}

interface SetInMod<in E> : CollectionInMod<E>, SubtypeSetInMod<E> {

    interface DelegateLesser<E> : CollectionInMod.DelegateLesser<E>, SubtypeSetInMod.DelegateLesser<E> {

        class Unsupported<E> : DelegateLesser<E>
    }
}

fun <E> inModDelegateTo(lesser: SetInMod.DelegateLesser<E>): SetInMod<E> =
    object : SetInMod<E>, CollectionInMod<E> by inModDelegateTo(lesser as CollectionInMod.DelegateLesser<E>) {}

fun <E> inModDelegateTo(delegate: MutableSet<E>): SetInMod<E> = object : SetInMod<E>, MutableSet<E> by delegate {}

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

fun <I, O : I> fromQmParted(
    subtype: MutableListIterator<O>,
    modification: SubtypeListIteratorInMod<I>,
): MutableListIterator<I> =
    object : MutableListIterator<I>, ListIterator<I> by subtype, ListIteratorInMod<I> by modification {
        override fun remove() = subtype.remove()
    }

// Collections

fun <I, O : I> fromQmParted(
    subtype: MutableCollection<O>,
    modification: SubtypeCollectionInMod<I>
): MutableCollection<I> =
    object : MutableCollection<I>, Collection<I> by subtype, SubtypeCollectionInMod<I> by modification {
        override fun iterator() = subtype.iterator()
        override fun remove(element: I) = subtype.remove(element)
        override fun removeAll(elements: Collection<I>) = subtype.removeAll(elements)
        override fun retainAll(elements: Collection<I>) = subtype.retainAll(elements)
        override fun clear() = subtype.clear()
    }

fun <I, O : I> fromQmParted(subtype: MutableList<O>, modification: SubtypeListInMod<I>): MutableList<I> =
    object : MutableList<I>, List<I> by subtype, ListInMod<I> by modification {
        override fun iterator() = subtype.iterator()
        override fun clear() = subtype.clear()
        override fun removeAt(index: Int) = subtype.removeAt(index)
        override fun listIterator(): MutableListIterator<I> =
            fromQmParted(subtype.listIterator(), modification.listIteratorInMod())

        override fun listIterator(index: Int): MutableListIterator<I> =
            fromQmParted(subtype.listIterator(index), modification.listIteratorInMod(index))

        override fun subList(fromIndex: Int, toIndex: Int) =
            fromQmParted(subtype.subList(fromIndex, toIndex), modification.subListInMod(fromIndex, toIndex))
    }

fun <I, O : I> fromQmParted(subtype: MutableSet<O>, modification: SubtypeSetInMod<I>): MutableSet<I> =
    object : MutableSet<I>, MutableCollection<I> by fromQmParted(subtype as MutableCollection<O>, modification) {}

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

package fyi.ioclub.commons.collection.qmparted

// Iterators

fun <Q> fromQmParted(query: Iterator<Q>, modification: IteratorMod): MutableIterator<Q> =
    object : MutableIterator<Q>, Iterator<Q> by query, IteratorMod by modification {}

fun <Q> fromQmParted(query: ListIterator<Q>, modification: ListIteratorMod<Q>): MutableListIterator<Q> =
    object : MutableListIterator<Q>, ListIterator<Q> by query, ListIteratorMod<Q> by modification {}

// Collections

fun <Q> fromQmParted(query: Collection<Q>, modification: CollectionMod<Q>): MutableCollection<Q> =
    object : MutableCollection<Q>, Collection<Q> by query, CollectionMod<Q> by modification {
        override fun iterator() = fromQmPartedIterator(query, modification)
    }

fun <Q> fromQmParted(query: List<Q>, modification: ListMod<Q>): MutableList<Q> =
    object : MutableList<Q>, List<Q> by query, ListMod<Q> by modification {
        override fun iterator() = fromQmPartedIterator(query, modification)
        override fun listIterator() = fromQmParted(query.listIterator(), modification.listIteratorMod())
        override fun listIterator(index: Int) = fromQmParted(query.listIterator(index), modification.listIteratorMod(index))
        override fun subList(fromIndex: Int, toIndex: Int) =
            fromQmParted(query.subList(fromIndex, toIndex), modification.subListMod(fromIndex, toIndex))
    }

fun <Q> fromQmParted(query: Set<Q>, modification: SetMod<Q>): MutableSet<Q> =
    object : MutableSet<Q>, Set<Q> by query, SetMod<Q> by modification {
        override fun iterator() = fromQmPartedIterator(query, modification)
    }

private fun <Q> fromQmPartedIterator(query: Collection<Q>, modification: CollectionMod<Q>) =
    fromQmParted(query.iterator(), modification.iteratorMod())

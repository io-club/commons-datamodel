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

fun <Q> fromQmParted(query: Iterator<Q>, modification: IteratorMod.DelegateLesser) =
    fromQmParted(query, modDelegateTo(modification))

fun <Q> fromQmParted(query: Iterator<Q>, modification: MutableIterator<Q>) =
    fromQmParted(query, modDelegateTo(modification))

fun <I, O : I> fromQmParted(
    subtype: MutableListIterator<O>, modification: SubtypeListIteratorInMod.DelegateLesser<I>,
): MutableListIterator<I> = fromQmParted(subtype, inModDelegateTo(modification))

fun <Q> fromQmParted(query: ListIterator<Q>, modification: ListIteratorMod.DelegateLesser<Q>) =
    fromQmParted(query, modDelegateTo(modification))

fun <Q> fromQmParted(query: ListIterator<Q>, modification: MutableListIterator<Q>) =
    fromQmParted(query, modDelegateTo(modification))

// Collections

fun <I, O : I> fromQmParted(
    subtype: MutableCollection<O>, modification: SubtypeCollectionInMod.DelegateLesser<I>,
): MutableCollection<I> = fromQmParted(subtype, inModDelegateTo(modification))

fun <Q> fromQmParted(query: Collection<Q>, modification: CollectionMod.DelegateLesser<Q>) =
    fromQmParted(query, modDelegateTo(modification))

fun <Q> fromQmParted(query: Collection<Q>, modification: MutableCollection<Q>) =
    fromQmParted(query, modDelegateTo(modification))

fun <I, O : I> fromQmParted(
    subtype: MutableList<O>, modification: SubtypeListInMod.DelegateLesser<I>,
): MutableList<I> = fromQmParted(subtype, inModDelegateTo(modification))

fun <Q> fromQmParted(query: List<Q>, modification: ListMod.DelegateLesser<Q>) =
    fromQmParted(query, modDelegateTo(modification))

fun <Q> fromQmParted(query: List<Q>, modification: MutableList<Q>) = fromQmParted(query, modDelegateTo(modification))

fun <I, O : I> fromQmParted(
    subtype: MutableSet<O>, modification: SubtypeSetInMod.DelegateLesser<I>,
): MutableSet<I> = fromQmParted(subtype, inModDelegateTo(modification))

fun <Q> fromQmParted(query: Set<Q>, modification: SetMod.DelegateLesser<Q>) =
    fromQmParted(query, modDelegateTo(modification))

fun <Q> fromQmParted(query: Set<Q>, modification: MutableSet<Q>) = fromQmParted(query, modDelegateTo(modification))

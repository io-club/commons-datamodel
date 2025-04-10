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

package fyi.ioclub.commons.collection.flatting

fun <T> Iterable<Iterable<T>>.flatten(): Iterable<T> = let(::FlattenedIterable)
fun <T> Collection<Collection<T>>.flatten(): Collection<T> = let(::FlattenedCollection)
fun <T> List<List<T>>.flatten(): List<T> = let(::FlattenedList)

operator fun <T> Iterable<T>.plus(other: Iterable<T>) = listOf(this, other).flatten()
operator fun <T> Collection<T>.plus(other: Collection<T>) = listOf(this, other).flatten()
operator fun <T> List<T>.plus(other: List<T>) = listOf(this, other).flatten()

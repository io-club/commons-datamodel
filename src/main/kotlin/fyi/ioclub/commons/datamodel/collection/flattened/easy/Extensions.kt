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

package fyi.ioclub.commons.datamodel.collection.flattened.easy

import fyi.ioclub.commons.datamodel.collection.flattened.flatten

infix fun <T> Iterable<T>.plusListOf(other: T): Iterable<T> = listOf(this, listOf(other)).flatten()
infix fun <T> Collection<T>.plusListOf(other: T): Collection<T> = listOf(this, listOf(other)).flatten()
infix fun <T> List<T>.plusListOf(other: T): List<T> = listOf(this, listOf(other)).flatten()

infix fun <T> T.listOfThisPlus(other: Iterable<T>): Iterable<T> = listOf(listOf(this), other).flatten()
infix fun <T> T.listOfThisPlus(other: Collection<T>): Collection<T> = listOf(listOf(this), other).flatten()
infix fun <T> T.listOfThisPlus(other: List<T>): List<T> = listOf(listOf(this), other).flatten()

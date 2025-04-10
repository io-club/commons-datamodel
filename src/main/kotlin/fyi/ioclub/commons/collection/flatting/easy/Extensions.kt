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

package fyi.ioclub.commons.collection.flatting.easy

import fyi.ioclub.commons.collection.flatting.flatten

operator fun <T> T.plus(other: Iterable<T>) = listOf(listOf(this), other).flatten()
operator fun <T> Iterable<T>.plus(other: T) = listOf(this, listOf(other)).flatten()
operator fun <T> T.plus(other: Collection<T>) = listOf(listOf(this), other).flatten()
operator fun <T> Collection<T>.plus(other: T) = listOf(this, listOf(other)).flatten()
operator fun <T> T.plus(other: List<T>) = listOf(listOf(this), other).flatten()
operator fun <T> List<T>.plus(other: T) = listOf(this, listOf(other)).flatten()

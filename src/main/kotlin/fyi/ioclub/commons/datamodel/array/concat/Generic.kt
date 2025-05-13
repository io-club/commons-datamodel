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

package fyi.ioclub.commons.datamodel.array.concat

import fyi.ioclub.commons.datamodel.array.slice.GenericArraySlice

fun <T> concat(clazz: Class<T>, source1: GenericArraySlice<T>, source2: GenericArraySlice<T>) =
    concat(source1, source2, destinationFactoryOf(clazz))

fun <T> concat(clazz: Class<T>, source1: GenericArraySlice<T>, source2: GenericArraySlice<T>, vararg sourceN: GenericArraySlice<T>) =
    concat(source1, source2, destinationFactoryOf(clazz))

fun <T> concat(clazz: Class<T>, sources: Collection<GenericArraySlice<T>>) = concat(sources, destinationFactoryOf(clazz))

private fun <T> destinationFactoryOf(clazz: Class<T>) =
    @Suppress("UNCHECKED_CAST") { size: Int -> java.lang.reflect.Array.newInstance(clazz, size) as Array<T> }

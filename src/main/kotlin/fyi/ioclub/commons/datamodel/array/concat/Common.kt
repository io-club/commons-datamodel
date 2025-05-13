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

import fyi.ioclub.commons.datamodel.array.slice.ArraySlice

/** @param A array type. */
internal inline fun <A : Any> concat(
    source1: ArraySlice<A>,
    source2: ArraySlice<A>,
    destinationFactory: (Int) -> A,
): A {
    val len1 = source1.length
    val len2 = source2.length
    val dst = destinationFactory(len1 + len2)
    System.arraycopy(source1.array, source1.offset, dst, 0, len1)
    System.arraycopy(source2.array, source2.offset, dst, len1, len2)
    return dst
}

/** @param A array type. */
internal inline fun <A : Any> concat(
    source1: ArraySlice<A>,
    source2: ArraySlice<A>,
    otherSources: Array<out ArraySlice<A>>,
    destinationFactory: (Int) -> A,
) = if (otherSources.isEmpty()) concat(source1, source2, destinationFactory)
else concat(listOf(source1, source2, *otherSources), destinationFactory)

/** @param A array type. */
internal inline fun <A : Any> concat(
    sources: Collection<ArraySlice<A>>,
    destinationFactory: (Int) -> A,
): A {
    val lenArr = IntArray(sources.size)
    val size = sources.foldIndexed(0) { i, lenSum, src ->
        src.length.let {
            lenArr[i] = it
            lenSum + it
        }
    }
    val dst = destinationFactory(size)
    var pos = 0
    sources.forEachIndexed { i, src ->
        val len = lenArr[i]
        System.arraycopy(src.array, src.offset, dst, pos, len)
        pos += len
    }
    return dst
}

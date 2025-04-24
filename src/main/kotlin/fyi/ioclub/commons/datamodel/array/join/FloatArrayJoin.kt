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

package fyi.ioclub.commons.datamodel.array.join

import fyi.ioclub.commons.datamodel.array.slice.FloatArraySlice
import fyi.ioclub.commons.datamodel.array.slice.arraySliceOf

@JvmOverloads
fun join(src1: FloatArray, off1: Int, len1: Int, src2: FloatArray, len2: Int = src2.size) =
    join(src1, off1, len1, src2, 0, len2)

fun join(src1: FloatArray, off1: Int, len1: Int, src2: FloatArray, off2: Int, len2: Int) = FloatArray(len1 + len2).also {
    System.arraycopy(src1, off1, it, 0, len1)
    System.arraycopy(src2, off2, it, len1, len2)
}

@JvmOverloads
fun join(src1: FloatArray, len1: Int, src2: FloatArray, len2: Int = src2.size) = join(src1, len1, src2, 0, len2)
fun join(src1: FloatArray, len1: Int, src2: FloatArray, off2: Int, len2: Int) = join(src1, 0, len1, src2, off2, len2)

@JvmOverloads
fun join(src1: FloatArray, src2: FloatArray, len2: Int = src2.size) = join(src1, src2, 0, len2)
fun join(src1: FloatArray, src2: FloatArray, off2: Int, len2: Int) = join(src1, src1.size, src2, off2, len2)

fun join(src1: FloatArraySlice, src2: FloatArraySlice) =
    join(src1.array, src1.offset, src1.length, src2.array, src2.offset, src2.length)

fun join(src1: FloatArraySlice, src2: FloatArray) = join(src1, arraySliceOf(src2))
fun join(src1: FloatArray, src2: FloatArraySlice) = join(arraySliceOf(src1), src2)

operator fun FloatArraySlice.plus(other: FloatArraySlice) = join(this, other)
operator fun FloatArraySlice.plus(other: FloatArray) = join(this, other)
operator fun FloatArray.plus(other: FloatArraySlice) = join(this, other)
operator fun FloatArray.plus(other: FloatArray) = join(this, other)

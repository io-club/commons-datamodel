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

import fyi.ioclub.commons.datamodel.array.slice.BooleanArraySlice
import fyi.ioclub.commons.datamodel.array.slice.arraySliceOf

@JvmOverloads
fun join(src1: BooleanArray, off1: Int, len1: Int, src2: BooleanArray, len2: Int = src2.size) =
    join(src1, off1, len1, src2, 0, len2)

fun join(src1: BooleanArray, off1: Int, len1: Int, src2: BooleanArray, off2: Int, len2: Int) = BooleanArray(len1 + len2).also {
    System.arraycopy(src1, off1, it, 0, len1)
    System.arraycopy(src2, off2, it, len1, len2)
}

@JvmOverloads
fun join(src1: BooleanArray, len1: Int, src2: BooleanArray, len2: Int = src2.size) = join(src1, len1, src2, 0, len2)
fun join(src1: BooleanArray, len1: Int, src2: BooleanArray, off2: Int, len2: Int) = join(src1, 0, len1, src2, off2, len2)

@JvmOverloads
fun join(src1: BooleanArray, src2: BooleanArray, len2: Int = src2.size) = join(src1, src2, 0, len2)
fun join(src1: BooleanArray, src2: BooleanArray, off2: Int, len2: Int) = join(src1, src1.size, src2, off2, len2)

fun join(src1: BooleanArraySlice, src2: BooleanArraySlice) =
    join(src1.array, src1.offset, src1.length, src2.array, src2.offset, src2.length)

fun join(src1: BooleanArraySlice, src2: BooleanArray) = join(src1, arraySliceOf(src2))
fun join(src1: BooleanArray, src2: BooleanArraySlice) = join(arraySliceOf(src1), src2)

operator fun BooleanArraySlice.plus(other: BooleanArraySlice) = join(this, other)
operator fun BooleanArraySlice.plus(other: BooleanArray) = join(this, other)
operator fun BooleanArray.plus(other: BooleanArraySlice) = join(this, other)
operator fun BooleanArray.plus(other: BooleanArray) = join(this, other)

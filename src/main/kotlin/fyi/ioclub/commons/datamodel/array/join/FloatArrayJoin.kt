/*
 * Copyright © 2025 IO Club
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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

/*
 * Copyright © 2025 IO Club
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package fyi.ioclub.commons.datamodel.array.slice.simplified

import fyi.ioclub.commons.datamodel.array.slice.ArraySlice
import fyi.ioclub.commons.datamodel.array.slice.BooleanArraySlice
import fyi.ioclub.commons.datamodel.array.slice.ByteArraySlice
import fyi.ioclub.commons.datamodel.array.slice.CharArraySlice
import fyi.ioclub.commons.datamodel.array.slice.DoubleArraySlice
import fyi.ioclub.commons.datamodel.array.slice.FloatArraySlice
import fyi.ioclub.commons.datamodel.array.slice.IntArraySlice
import fyi.ioclub.commons.datamodel.array.slice.LongArraySlice
import fyi.ioclub.commons.datamodel.array.slice.ShortArraySlice
import fyi.ioclub.commons.datamodel.array.slice.arraySliceOf

operator fun <T> Array<T>.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

operator fun BooleanArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ByteArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun CharArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun DoubleArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun FloatArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun IntArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun LongArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ShortArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

operator fun <T> ArraySlice<T>.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

operator fun BooleanArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ByteArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun CharArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun DoubleArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun FloatArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun IntArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun LongArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ShortArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

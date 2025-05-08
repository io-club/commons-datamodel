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

package fyi.ioclub.commons.datamodel.array.slice.easy

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

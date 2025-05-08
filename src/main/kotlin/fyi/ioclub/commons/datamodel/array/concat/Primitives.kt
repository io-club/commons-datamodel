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

import fyi.ioclub.commons.datamodel.array.slice.*

fun concat(source1: BooleanArraySlice, source2: BooleanArraySlice, vararg sourceN: BooleanArraySlice) =
    concat(source1, source2, sourceN, ::BooleanArray)

fun concat(source1: ByteArraySlice, source2: ByteArraySlice, vararg sourceN: ByteArraySlice) =
    concat(source1, source2, sourceN, ::ByteArray)

fun concat(source1: CharArraySlice, source2: CharArraySlice, vararg sourceN: CharArraySlice) =
    concat(source1, source2, sourceN, ::CharArray)

fun concat(source1: DoubleArraySlice, source2: DoubleArraySlice, vararg sourceN: DoubleArraySlice) =
    concat(source1, source2, sourceN, ::DoubleArray)

fun concat(source1: FloatArraySlice, source2: FloatArraySlice, vararg sourceN: FloatArraySlice) =
    concat(source1, source2, sourceN, ::FloatArray)

fun concat(source1: IntArraySlice, source2: IntArraySlice, vararg sourceN: IntArraySlice) =
    concat(source1, source2, sourceN, ::IntArray)

fun concat(source1: LongArraySlice, source2: LongArraySlice, vararg sourceN: LongArraySlice) =
    concat(source1, source2, sourceN, ::LongArray)

fun concat(source1: ShortArraySlice, source2: ShortArraySlice, vararg sourceN: ShortArraySlice) =
    concat(source1, source2, sourceN, ::ShortArray)

fun concat(sources: Collection<BooleanArraySlice>) = concat(sources, ::BooleanArray)
fun concat(sources: Collection<ByteArraySlice>) = concat(sources, ::ByteArray)
fun concat(sources: Collection<CharArraySlice>) = concat(sources, ::CharArray)
fun concat(sources: Collection<DoubleArraySlice>) = concat(sources, ::DoubleArray)
fun concat(sources: Collection<FloatArraySlice>) = concat(sources, ::FloatArray)
fun concat(sources: Collection<IntArraySlice>) = concat(sources, ::IntArray)
fun concat(sources: Collection<LongArraySlice>) = concat(sources, ::LongArray)
fun concat(sources: Collection<ShortArraySlice>) = concat(sources, ::ShortArray)

infix fun BooleanArraySlice.concat(other: BooleanArraySlice) = concat(this, other, ::BooleanArray)
infix fun ByteArraySlice.concat(other: ByteArraySlice) = concat(this, other, ::ByteArray)
infix fun CharArraySlice.concat(other: CharArraySlice) = concat(this, other, ::CharArray)
infix fun DoubleArraySlice.concat(other: DoubleArraySlice) = concat(this, other, ::DoubleArray)
infix fun FloatArraySlice.concat(other: FloatArraySlice) = concat(this, other, ::FloatArray)
infix fun IntArraySlice.concat(other: IntArraySlice) = concat(this, other, ::IntArray)
infix fun LongArraySlice.concat(other: LongArraySlice) = concat(this, other, ::LongArray)
infix fun ShortArraySlice.concat(other: ShortArraySlice) = concat(this, other, ::ShortArray)

operator fun BooleanArraySlice.plus(other: BooleanArraySlice) = this concat other
operator fun ByteArraySlice.plus(other: ByteArraySlice) = this concat other
operator fun CharArraySlice.plus(other: CharArraySlice) = this concat other
operator fun DoubleArraySlice.plus(other: DoubleArraySlice) = this concat other
operator fun FloatArraySlice.plus(other: FloatArraySlice) = this concat other
operator fun IntArraySlice.plus(other: IntArraySlice) = this concat other
operator fun LongArraySlice.plus(other: LongArraySlice) = this concat other
operator fun ShortArraySlice.plus(other: ShortArraySlice) = this concat other

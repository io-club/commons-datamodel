/*
 * Copyright © 2025 IO Club
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package fyi.ioclub.commons.datamodel.array.slice

sealed interface PrimitiveArraySlice<A> : ArraySliceProtocol<A>

interface BooleanArraySlice : PrimitiveArraySlice<BooleanArray>
interface ByteArraySlice : PrimitiveArraySlice<ByteArray>
interface CharArraySlice : PrimitiveArraySlice<CharArray>
interface DoubleArraySlice : PrimitiveArraySlice<DoubleArray>
interface FloatArraySlice : PrimitiveArraySlice<FloatArray>
interface IntArraySlice : PrimitiveArraySlice<IntArray>
interface LongArraySlice : PrimitiveArraySlice<LongArray>
interface ShortArraySlice : PrimitiveArraySlice<ShortArray>

// Factories

fun arraySliceOf(array: BooleanArray, offset: Int, length: Int): BooleanArraySlice =
    BooleanArraySliceImpl(array, offset, length)

fun arraySliceOf(array: ByteArray, offset: Int, length: Int): ByteArraySlice = ByteArraySliceImpl(array, offset, length)
fun arraySliceOf(array: CharArray, offset: Int, length: Int): CharArraySlice = CharArraySliceImpl(array, offset, length)
fun arraySliceOf(array: DoubleArray, offset: Int, length: Int): DoubleArraySlice =
    DoubleArraySliceImpl(array, offset, length)

fun arraySliceOf(array: FloatArray, offset: Int, length: Int): FloatArraySlice =
    FloatArraySliceImpl(array, offset, length)

fun arraySliceOf(array: IntArray, offset: Int, length: Int): IntArraySlice = IntArraySliceImpl(array, offset, length)
fun arraySliceOf(array: LongArray, offset: Int, length: Int): LongArraySlice = LongArraySliceImpl(array, offset, length)
fun arraySliceOf(array: ShortArray, offset: Int, length: Int): ShortArraySlice =
    ShortArraySliceImpl(array, offset, length)

fun arraySliceOf(array: BooleanArray) = arraySliceOf(array, 0, array.size)
fun arraySliceOf(array: ByteArray) = arraySliceOf(array, 0, array.size)
fun arraySliceOf(array: CharArray) = arraySliceOf(array, 0, array.size)
fun arraySliceOf(array: DoubleArray) = arraySliceOf(array, 0, array.size)
fun arraySliceOf(array: FloatArray) = arraySliceOf(array, 0, array.size)
fun arraySliceOf(array: IntArray) = arraySliceOf(array, 0, array.size)
fun arraySliceOf(array: LongArray) = arraySliceOf(array, 0, array.size)
fun arraySliceOf(array: ShortArray) = arraySliceOf(array, 0, array.size)

fun arraySliceOf(arraySlice: BooleanArraySlice): BooleanArraySlice = arraySlice
fun arraySliceOf(arraySlice: ByteArraySlice): ByteArraySlice = arraySlice
fun arraySliceOf(arraySlice: CharArraySlice): CharArraySlice = arraySlice
fun arraySliceOf(arraySlice: DoubleArraySlice): DoubleArraySlice = arraySlice
fun arraySliceOf(arraySlice: FloatArraySlice): FloatArraySlice = arraySlice
fun arraySliceOf(arraySlice: IntArraySlice): IntArraySlice = arraySlice
fun arraySliceOf(arraySlice: LongArraySlice): LongArraySlice = arraySlice
fun arraySliceOf(arraySlice: ShortArraySlice): ShortArraySlice = arraySlice

fun arraySliceOf(arraySlice: BooleanArraySlice, offset: Int, length: Int): BooleanArraySlice =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

fun arraySliceOf(arraySlice: ByteArraySlice, offset: Int, length: Int): ByteArraySlice =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

fun arraySliceOf(arraySlice: CharArraySlice, offset: Int, length: Int): CharArraySlice =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

fun arraySliceOf(arraySlice: DoubleArraySlice, offset: Int, length: Int): DoubleArraySlice =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

fun arraySliceOf(arraySlice: FloatArraySlice, offset: Int, length: Int): FloatArraySlice =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

fun arraySliceOf(arraySlice: IntArraySlice, offset: Int, length: Int): IntArraySlice =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

fun arraySliceOf(arraySlice: LongArraySlice, offset: Int, length: Int): LongArraySlice =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

fun arraySliceOf(arraySlice: ShortArraySlice, offset: Int, length: Int): ShortArraySlice =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

// Implements

private class BooleanArraySliceImpl(
    override val array: BooleanArray,
    override val offset: Int,
    override val length: Int,
) :
    BooleanArraySlice {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, BooleanArray::size, BooleanArray::copyOfRange)
}

private class ByteArraySliceImpl(override val array: ByteArray, override val offset: Int, override val length: Int) :
    ByteArraySlice {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, ByteArray::size, ByteArray::copyOfRange)
}

private class CharArraySliceImpl(override val array: CharArray, override val offset: Int, override val length: Int) :
    CharArraySlice {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, CharArray::size, CharArray::copyOfRange)
}

private class DoubleArraySliceImpl(
    override val array: DoubleArray,
    override val offset: Int,
    override val length: Int,
) :
    DoubleArraySlice {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, DoubleArray::size, DoubleArray::copyOfRange)
}

private class FloatArraySliceImpl(override val array: FloatArray, override val offset: Int, override val length: Int) :
    FloatArraySlice {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, FloatArray::size, FloatArray::copyOfRange)
}

private class IntArraySliceImpl(override val array: IntArray, override val offset: Int, override val length: Int) :
    IntArraySlice {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, IntArray::size, IntArray::copyOfRange)
}

private class LongArraySliceImpl(override val array: LongArray, override val offset: Int, override val length: Int) :
    LongArraySlice {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, LongArray::size, LongArray::copyOfRange)
}

private class ShortArraySliceImpl(override val array: ShortArray, override val offset: Int, override val length: Int) :
    ShortArraySlice {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, ShortArray::size, ShortArray::copyOfRange)
}

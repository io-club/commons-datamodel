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

package fyi.ioclub.commons.datamodel.array.slice

import java.util.*

// Interfaces

sealed interface PrimitiveArraySlice<E : Comparable<E>, out A : Any> : ArraySlice.Typed.Comparable<E, A>

abstract class BooleanArraySlice : PrimitiveArraySlice<Boolean, BooleanArray> {

    final override infix fun contentEquals(other: ArraySlice<BooleanArray>) = contentEquals(other as BooleanArraySlice)
    abstract infix fun contentEquals(other: BooleanArraySlice): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in Boolean, BooleanArray>): BooleanArraySlice =
        copyInto(destination as BooleanArraySlice)

    abstract fun copyInto(destination: BooleanArraySlice): BooleanArraySlice

    interface Delegate : ArraySlice.OutDelegate.Typed.Comparable<Boolean, BooleanArray> {

        infix fun contentEquals(other: BooleanArraySlice): Boolean = contentEqualsDefaultTmpl(other)

        fun copyInto(destination: BooleanArraySlice): BooleanArraySlice = throw UnsupportedOperationException()
    }
}

fun BooleanArraySlice(delegate: BooleanArraySlice.Delegate): BooleanArraySlice =
    object : BooleanArraySlice.Delegate by delegate, BooleanArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class ByteArraySlice : PrimitiveArraySlice<Byte, ByteArray> {

    final override infix fun contentEquals(other: ArraySlice<ByteArray>) = contentEquals(other as ByteArraySlice)
    abstract infix fun contentEquals(other: ByteArraySlice): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in Byte, ByteArray>): ByteArraySlice =
        copyInto(destination as ByteArraySlice)

    abstract fun copyInto(destination: ByteArraySlice): ByteArraySlice

    interface Delegate : ArraySlice.OutDelegate.Typed.Comparable<Byte, ByteArray> {

        infix fun contentEquals(other: ByteArraySlice): Boolean = contentEqualsDefaultTmpl(other)

        fun copyInto(destination: ByteArraySlice): ByteArraySlice = throw UnsupportedOperationException()
    }
}

fun ByteArraySlice(delegate: ByteArraySlice.Delegate): ByteArraySlice =
    object : ByteArraySlice.Delegate by delegate, ByteArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class CharArraySlice : PrimitiveArraySlice<Char, CharArray> {

    final override infix fun contentEquals(other: ArraySlice<CharArray>) = contentEquals(other as CharArraySlice)
    abstract infix fun contentEquals(other: CharArraySlice): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in Char, CharArray>): CharArraySlice =
        copyInto(destination as CharArraySlice)

    abstract fun copyInto(destination: CharArraySlice): CharArraySlice

    interface Delegate : ArraySlice.OutDelegate.Typed.Comparable<Char, CharArray> {

        infix fun contentEquals(other: CharArraySlice): Boolean = contentEqualsDefaultTmpl(other)

        fun copyInto(destination: CharArraySlice): CharArraySlice = throw UnsupportedOperationException()
    }
}

fun CharArraySlice(delegate: CharArraySlice.Delegate): CharArraySlice =
    object : CharArraySlice.Delegate by delegate, CharArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class DoubleArraySlice : PrimitiveArraySlice<Double, DoubleArray> {

    final override infix fun contentEquals(other: ArraySlice<DoubleArray>) = contentEquals(other as DoubleArraySlice)
    abstract infix fun contentEquals(other: DoubleArraySlice): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in Double, DoubleArray>): DoubleArraySlice =
        copyInto(destination as DoubleArraySlice)

    abstract fun copyInto(destination: DoubleArraySlice): DoubleArraySlice

    interface Delegate : ArraySlice.OutDelegate.Typed.Comparable<Double, DoubleArray> {

        infix fun contentEquals(other: DoubleArraySlice): Boolean = contentEqualsDefaultTmpl(other)

        fun copyInto(destination: DoubleArraySlice): DoubleArraySlice = throw UnsupportedOperationException()
    }
}

fun DoubleArraySlice(delegate: DoubleArraySlice.Delegate): DoubleArraySlice =
    object : DoubleArraySlice.Delegate by delegate, DoubleArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class FloatArraySlice : PrimitiveArraySlice<Float, FloatArray> {

    final override infix fun contentEquals(other: ArraySlice<FloatArray>) = contentEquals(other as FloatArraySlice)
    abstract infix fun contentEquals(other: FloatArraySlice): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in Float, FloatArray>): FloatArraySlice =
        copyInto(destination as FloatArraySlice)

    abstract fun copyInto(destination: FloatArraySlice): FloatArraySlice

    interface Delegate : ArraySlice.OutDelegate.Typed.Comparable<Float, FloatArray> {

        infix fun contentEquals(other: FloatArraySlice): Boolean = contentEqualsDefaultTmpl(other)

        fun copyInto(destination: FloatArraySlice): FloatArraySlice = throw UnsupportedOperationException()
    }
}

fun FloatArraySlice(delegate: FloatArraySlice.Delegate): FloatArraySlice =
    object : FloatArraySlice.Delegate by delegate, FloatArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class IntArraySlice : PrimitiveArraySlice<Int, IntArray> {

    final override infix fun contentEquals(other: ArraySlice<IntArray>) = contentEquals(other as IntArraySlice)
    abstract infix fun contentEquals(other: IntArraySlice): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in Int, IntArray>): IntArraySlice =
        copyInto(destination as IntArraySlice)

    abstract fun copyInto(destination: IntArraySlice): IntArraySlice

    interface Delegate : ArraySlice.OutDelegate.Typed.Comparable<Int, IntArray> {

        infix fun contentEquals(other: IntArraySlice): Boolean = contentEqualsDefaultTmpl(other)

        fun copyInto(destination: IntArraySlice): IntArraySlice = throw UnsupportedOperationException()
    }
}

fun IntArraySlice(delegate: IntArraySlice.Delegate): IntArraySlice =
    object : IntArraySlice.Delegate by delegate, IntArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class LongArraySlice : PrimitiveArraySlice<Long, LongArray> {

    final override infix fun contentEquals(other: ArraySlice<LongArray>) = contentEquals(other as LongArraySlice)
    abstract infix fun contentEquals(other: LongArraySlice): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in Long, LongArray>): LongArraySlice =
        copyInto(destination as LongArraySlice)

    abstract fun copyInto(destination: LongArraySlice): LongArraySlice

    interface Delegate : ArraySlice.OutDelegate.Typed.Comparable<Long, LongArray> {

        infix fun contentEquals(other: LongArraySlice): Boolean = contentEqualsDefaultTmpl(other)

        fun copyInto(destination: LongArraySlice): LongArraySlice = throw UnsupportedOperationException()
    }
}

fun LongArraySlice(delegate: LongArraySlice.Delegate): LongArraySlice =
    object : LongArraySlice.Delegate by delegate, LongArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class ShortArraySlice : PrimitiveArraySlice<Short, ShortArray> {

    final override infix fun contentEquals(other: ArraySlice<ShortArray>) = contentEquals(other as ShortArraySlice)
    abstract infix fun contentEquals(other: ShortArraySlice): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in Short, ShortArray>): ShortArraySlice =
        copyInto(destination as ShortArraySlice)

    abstract fun copyInto(destination: ShortArraySlice): ShortArraySlice

    interface Delegate : ArraySlice.OutDelegate.Typed.Comparable<Short, ShortArray> {

        infix fun contentEquals(other: ShortArraySlice): Boolean = contentEqualsDefaultTmpl(other)

        fun copyInto(destination: ShortArraySlice): ShortArraySlice = throw UnsupportedOperationException()
    }
}

fun ShortArraySlice(delegate: ShortArraySlice.Delegate): ShortArraySlice =
    object : ShortArraySlice.Delegate by delegate, ShortArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

// To generic array slice

fun BooleanArraySlice.toTypedArraySlice(): GenericArraySlice.Comparable<Boolean> =
    array.toTypedArray().asSlice(offset, length)

fun ByteArraySlice.toTypedArraySlice(): GenericArraySlice.Comparable<Byte> =
    array.toTypedArray().asSlice(offset, length)

fun CharArraySlice.toTypedArraySlice(): GenericArraySlice.Comparable<Char> =
    array.toTypedArray().asSlice(offset, length)

fun DoubleArraySlice.toTypedArraySlice(): GenericArraySlice.Comparable<Double> =
    array.toTypedArray().asSlice(offset, length)

fun FloatArraySlice.toTypedArraySlice(): GenericArraySlice.Comparable<Float> =
    array.toTypedArray().asSlice(offset, length)

fun IntArraySlice.toTypedArraySlice(): GenericArraySlice.Comparable<Int> =
    array.toTypedArray().asSlice(offset, length)

fun LongArraySlice.toTypedArraySlice(): GenericArraySlice.Comparable<Long> =
    array.toTypedArray().asSlice(offset, length)

fun ShortArraySlice.toTypedArraySlice(): GenericArraySlice.Comparable<Short> =
    array.toTypedArray().asSlice(offset, length)

// Factories for arrays

fun BooleanArray.asSlice() = asSlice(0, size)
fun ByteArray.asSlice() = asSlice(0, size)
fun CharArray.asSlice() = asSlice(0, size)
fun DoubleArray.asSlice() = asSlice(0, size)
fun FloatArray.asSlice() = asSlice(0, size)
fun IntArray.asSlice() = asSlice(0, size)
fun LongArray.asSlice() = asSlice(0, size)
fun ShortArray.asSlice() = asSlice(0, size)

fun BooleanArray.asSliceTo(to: Int) = asSliceFrom(0, to)
fun ByteArray.asSliceTo(to: Int) = asSliceFrom(0, to)
fun CharArray.asSliceTo(to: Int) = asSliceFrom(0, to)
fun DoubleArray.asSliceTo(to: Int) = asSliceFrom(0, to)
fun FloatArray.asSliceTo(to: Int) = asSliceFrom(0, to)
fun IntArray.asSliceTo(to: Int) = asSliceFrom(0, to)
fun LongArray.asSliceTo(to: Int) = asSliceFrom(0, to)
fun ShortArray.asSliceTo(to: Int) = asSliceFrom(0, to)

@JvmOverloads
fun BooleanArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

@JvmOverloads
fun ByteArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

@JvmOverloads
fun CharArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

@JvmOverloads
fun DoubleArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

@JvmOverloads
fun FloatArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

@JvmOverloads
fun IntArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

@JvmOverloads
fun LongArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

@JvmOverloads
fun ShortArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

fun BooleanArray.asSlice(offset: Int, length: Int): BooleanArraySlice =
    asSliceTmpl(size, offset, length, ::BooleanArraySliceDelegateImpl, ::BooleanArraySlice)

fun ByteArray.asSlice(offset: Int, length: Int): ByteArraySlice =
    asSliceTmpl(size, offset, length, ::ByteArraySliceDelegateImpl, ::ByteArraySlice)

fun CharArray.asSlice(offset: Int, length: Int): CharArraySlice =
    asSliceTmpl(size, offset, length, ::CharArraySliceDelegateImpl, ::CharArraySlice)

fun DoubleArray.asSlice(offset: Int, length: Int): DoubleArraySlice =
    asSliceTmpl(size, offset, length, ::DoubleArraySliceDelegateImpl, ::DoubleArraySlice)

fun FloatArray.asSlice(offset: Int, length: Int): FloatArraySlice =
    asSliceTmpl(size, offset, length, ::FloatArraySliceDelegateImpl, ::FloatArraySlice)

fun IntArray.asSlice(offset: Int, length: Int): IntArraySlice =
    asSliceTmpl(size, offset, length, ::IntArraySliceDelegateImpl, ::IntArraySlice)

fun LongArray.asSlice(offset: Int, length: Int): LongArraySlice =
    asSliceTmpl(size, offset, length, ::LongArraySliceDelegateImpl, ::LongArraySlice)

fun ShortArray.asSlice(offset: Int, length: Int) =
    asSliceTmpl(size, offset, length, ::ShortArraySliceDelegateImpl, ::ShortArraySlice)

// Factories for array slices

fun BooleanArraySlice.asSlice(): BooleanArraySlice = this
fun ByteArraySlice.asSlice(): ByteArraySlice = this
fun CharArraySlice.asSlice(): CharArraySlice = this
fun DoubleArraySlice.asSlice(): DoubleArraySlice = this
fun FloatArraySlice.asSlice(): FloatArraySlice = this
fun IntArraySlice.asSlice(): IntArraySlice = this
fun LongArraySlice.asSlice(): LongArraySlice = this
fun ShortArraySlice.asSlice(): ShortArraySlice = this

fun BooleanArraySlice.asSliceTo(to: Int) = asSliceFrom(0, to)
fun ByteArraySlice.asSliceTo(to: Int) = asSliceFrom(0, to)
fun CharArraySlice.asSliceTo(to: Int) = asSliceFrom(0, to)
fun DoubleArraySlice.asSliceTo(to: Int) = asSliceFrom(0, to)
fun FloatArraySlice.asSliceTo(to: Int) = asSliceFrom(0, to)
fun IntArraySlice.asSliceTo(to: Int) = asSliceFrom(0, to)
fun LongArraySlice.asSliceTo(to: Int) = asSliceFrom(0, to)
fun ShortArraySlice.asSliceTo(to: Int) = asSliceFrom(0, to)

@JvmOverloads
fun BooleanArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

@JvmOverloads
fun ByteArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

@JvmOverloads
fun CharArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

@JvmOverloads
fun DoubleArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

@JvmOverloads
fun FloatArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

@JvmOverloads
fun IntArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

@JvmOverloads
fun LongArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

@JvmOverloads
fun ShortArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

fun BooleanArraySlice.asSlice(offset: Int, length: Int) =
    asSliceTmpl(offset, length, ::BooleanArraySliceDelegateImpl, ::BooleanArraySlice)

fun ByteArraySlice.asSlice(offset: Int, length: Int) =
    asSliceTmpl(offset, length, ::ByteArraySliceDelegateImpl, ::ByteArraySlice)

fun CharArraySlice.asSlice(offset: Int, length: Int) =
    asSliceTmpl(offset, length, ::CharArraySliceDelegateImpl, ::CharArraySlice)

fun DoubleArraySlice.asSlice(offset: Int, length: Int) =
    asSliceTmpl(offset, length, ::DoubleArraySliceDelegateImpl, ::DoubleArraySlice)

fun FloatArraySlice.asSlice(offset: Int, length: Int) =
    asSliceTmpl(offset, length, ::FloatArraySliceDelegateImpl, ::FloatArraySlice)

fun IntArraySlice.asSlice(offset: Int, length: Int) =
    asSliceTmpl(offset, length, ::IntArraySliceDelegateImpl, ::IntArraySlice)

fun LongArraySlice.asSlice(offset: Int, length: Int) =
    asSliceTmpl(offset, length, ::LongArraySliceDelegateImpl, ::LongArraySlice)

fun ShortArraySlice.asSlice(offset: Int, length: Int) =
    asSliceTmpl(offset, length, ::ShortArraySliceDelegateImpl, ::ShortArraySlice)

// Implementations

private class BooleanArraySliceDelegateImpl(array: BooleanArray, offset: Int, length: Int) : BooleanArraySlice.Delegate,
    ArraySliceDelegateImplBase<BooleanArray>(BooleanArraySlice::class, array, offset, length) {

    override fun toSlicedArray() = toSlicedArrayTmpl(BooleanArray::copyOfRange)
    override infix fun contentEquals(other: BooleanArraySlice) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, BooleanArray::get, BooleanArray::set)
    override fun copyInto(destination: BooleanArraySlice) = copyIntoTmpl(destination)
    override fun fill(element: Boolean) = fillTmpl(element, BooleanArray::fill)

    override fun arrayBinarySearch(element: Boolean) = toTyped().arrayBinarySearch(element)

    override fun sort() {
        val typed = toTyped()
        typed.sort()
        val src = typed.array
        val dst = this.array
        for (i in typed.arrayIndices) dst[i] = src[i]
    }

    private fun toTyped() = let(::BooleanArraySlice).toTypedArraySlice()
}

private class ByteArraySliceDelegateImpl(array: ByteArray, offset: Int, length: Int) : ByteArraySlice.Delegate,
    ArraySliceDelegateImplBase<ByteArray>(ByteArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = toSlicedArrayTmpl(ByteArray::copyOfRange)
    override infix fun contentEquals(other: ByteArraySlice) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, ByteArray::get, ByteArray::set)
    override fun copyInto(destination: ByteArraySlice) = copyIntoTmpl(destination)
    override fun fill(element: Byte) = fillTmpl(element, ByteArray::fill)
    override fun arrayBinarySearch(element: Byte) = arrayBinarySearchTmpl(element, ByteArray::binarySearch)
    override fun sort() = sortTmpl(ByteArray::sort)
}

private class CharArraySliceDelegateImpl(array: CharArray, offset: Int, length: Int) : CharArraySlice.Delegate,
    ArraySliceDelegateImplBase<CharArray>(CharArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = toSlicedArrayTmpl(CharArray::copyOfRange)
    override infix fun contentEquals(other: CharArraySlice) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, CharArray::get, CharArray::set)
    override fun copyInto(destination: CharArraySlice) = copyIntoTmpl(destination)
    override fun fill(element: Char) = fillTmpl(element, CharArray::fill)
    override fun arrayBinarySearch(element: Char) = arrayBinarySearchTmpl(element, CharArray::binarySearch)
    override fun sort() = sortTmpl(CharArray::sort)
}

private class DoubleArraySliceDelegateImpl(array: DoubleArray, offset: Int, length: Int) : DoubleArraySlice.Delegate,
    ArraySliceDelegateImplBase<DoubleArray>(DoubleArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = toSlicedArrayTmpl(DoubleArray::copyOfRange)
    override infix fun contentEquals(other: DoubleArraySlice) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, DoubleArray::get, DoubleArray::set)
    override fun copyInto(destination: DoubleArraySlice) = copyIntoTmpl(destination)
    override fun fill(element: Double) = fillTmpl(element, DoubleArray::fill)
    override fun arrayBinarySearch(element: Double) = arrayBinarySearchTmpl(element, DoubleArray::binarySearch)
    override fun sort() = sortTmpl(DoubleArray::sort)
}

private class FloatArraySliceDelegateImpl(array: FloatArray, offset: Int, length: Int) : FloatArraySlice.Delegate,
    ArraySliceDelegateImplBase<FloatArray>(FloatArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = toSlicedArrayTmpl(FloatArray::copyOfRange)
    override infix fun contentEquals(other: FloatArraySlice) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, FloatArray::get, FloatArray::set)
    override fun copyInto(destination: FloatArraySlice) = copyIntoTmpl(destination)
    override fun fill(element: Float) = fillTmpl(element, FloatArray::fill)
    override fun arrayBinarySearch(element: Float) = arrayBinarySearchTmpl(element, FloatArray::binarySearch)
    override fun sort() = sortTmpl(FloatArray::sort)
}

private class IntArraySliceDelegateImpl(array: IntArray, offset: Int, length: Int) : IntArraySlice.Delegate,
    ArraySliceDelegateImplBase<IntArray>(IntArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = toSlicedArrayTmpl(IntArray::copyOfRange)
    override infix fun contentEquals(other: IntArraySlice) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, IntArray::get, IntArray::set)
    override fun copyInto(destination: IntArraySlice) = copyIntoTmpl(destination)
    override fun fill(element: Int) = fillTmpl(element, IntArray::fill)
    override fun arrayBinarySearch(element: Int) = arrayBinarySearchTmpl(element, IntArray::binarySearch)
    override fun sort() = sortTmpl(IntArray::sort)
}

private class LongArraySliceDelegateImpl(array: LongArray, offset: Int, length: Int) : LongArraySlice.Delegate,
    ArraySliceDelegateImplBase<LongArray>(LongArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = toSlicedArrayTmpl(LongArray::copyOfRange)
    override infix fun contentEquals(other: LongArraySlice) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, LongArray::get, LongArray::set)
    override fun copyInto(destination: LongArraySlice) = copyIntoTmpl(destination)
    override fun fill(element: Long) = fillTmpl(element, LongArray::fill)
    override fun arrayBinarySearch(element: Long) = arrayBinarySearchTmpl(element, LongArray::binarySearch)
    override fun sort() = sortTmpl(LongArray::sort)
}

private class ShortArraySliceDelegateImpl(array: ShortArray, offset: Int, length: Int) : ShortArraySlice.Delegate,
    ArraySliceDelegateImplBase<ShortArray>(ShortArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = toSlicedArrayTmpl(ShortArray::copyOfRange)
    override infix fun contentEquals(other: ShortArraySlice) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, ShortArray::get, ShortArray::set)
    override fun copyInto(destination: ShortArraySlice) = copyIntoTmpl(destination)
    override fun fill(element: Short) = fillTmpl(element, ShortArray::fill)
    override fun arrayBinarySearch(element: Short) = arrayBinarySearchTmpl(element, ShortArray::binarySearch)
    override fun sort() = sortTmpl(ShortArray::sort)
}

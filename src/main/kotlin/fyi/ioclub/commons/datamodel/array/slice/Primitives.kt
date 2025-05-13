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

sealed interface PrimitiveArraySlice<out A : Any> : ArraySlice<A>

// Interfaces

abstract class BooleanArraySlice : PrimitiveArraySlice<BooleanArray> {

    final override infix fun contentEquals(other: ArraySlice<BooleanArray>) = contentEquals(other as BooleanArraySlice)

    abstract infix fun contentEquals(other: BooleanArraySlice): Boolean

    interface Delegate : ArraySlice.OutDelegate<BooleanArray> {

        infix fun contentEquals(other: BooleanArraySlice): Boolean
    }
}

fun BooleanArraySlice(delegate: BooleanArraySlice.Delegate): BooleanArraySlice =
    object : BooleanArraySlice.Delegate by delegate, BooleanArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class ByteArraySlice : PrimitiveArraySlice<ByteArray> {

    final override infix fun contentEquals(other: ArraySlice<ByteArray>) = contentEquals(other as ByteArraySlice)

    abstract infix fun contentEquals(other: ByteArraySlice): Boolean

    interface Delegate : ArraySlice.OutDelegate<ByteArray> {

        infix fun contentEquals(other: ByteArraySlice): Boolean
    }
}

fun ByteArraySlice(delegate: ByteArraySlice.Delegate): ByteArraySlice =
    object : ByteArraySlice.Delegate by delegate, ByteArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class CharArraySlice : PrimitiveArraySlice<CharArray> {

    final override infix fun contentEquals(other: ArraySlice<CharArray>) = contentEquals(other as CharArraySlice)

    abstract infix fun contentEquals(other: CharArraySlice): Boolean

    interface Delegate : ArraySlice.OutDelegate<CharArray> {

        infix fun contentEquals(other: CharArraySlice): Boolean
    }
}

fun CharArraySlice(delegate: CharArraySlice.Delegate): CharArraySlice =
    object : CharArraySlice.Delegate by delegate, CharArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class DoubleArraySlice : PrimitiveArraySlice<DoubleArray> {

    final override infix fun contentEquals(other: ArraySlice<DoubleArray>) = contentEquals(other as DoubleArraySlice)

    abstract infix fun contentEquals(other: DoubleArraySlice): Boolean

    interface Delegate : ArraySlice.OutDelegate<DoubleArray> {

        infix fun contentEquals(other: DoubleArraySlice): Boolean
    }
}

fun DoubleArraySlice(delegate: DoubleArraySlice.Delegate): DoubleArraySlice =
    object : DoubleArraySlice.Delegate by delegate, DoubleArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class FloatArraySlice : PrimitiveArraySlice<FloatArray> {

    final override infix fun contentEquals(other: ArraySlice<FloatArray>) = contentEquals(other as FloatArraySlice)

    abstract infix fun contentEquals(other: FloatArraySlice): Boolean

    interface Delegate : ArraySlice.OutDelegate<FloatArray> {

        infix fun contentEquals(other: FloatArraySlice): Boolean
    }
}

fun FloatArraySlice(delegate: FloatArraySlice.Delegate): FloatArraySlice =
    object : FloatArraySlice.Delegate by delegate, FloatArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class IntArraySlice : PrimitiveArraySlice<IntArray> {

    final override infix fun contentEquals(other: ArraySlice<IntArray>) = contentEquals(other as IntArraySlice)

    abstract infix fun contentEquals(other: IntArraySlice): Boolean

    interface Delegate : ArraySlice.OutDelegate<IntArray> {

        infix fun contentEquals(other: IntArraySlice): Boolean
    }
}

fun IntArraySlice(delegate: IntArraySlice.Delegate): IntArraySlice =
    object : IntArraySlice.Delegate by delegate, IntArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class LongArraySlice : PrimitiveArraySlice<LongArray> {

    final override infix fun contentEquals(other: ArraySlice<LongArray>) = contentEquals(other as LongArraySlice)

    abstract infix fun contentEquals(other: LongArraySlice): Boolean

    interface Delegate : ArraySlice.OutDelegate<LongArray> {

        infix fun contentEquals(other: LongArraySlice): Boolean
    }
}

fun LongArraySlice(delegate: LongArraySlice.Delegate): LongArraySlice =
    object : LongArraySlice.Delegate by delegate, LongArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

abstract class ShortArraySlice : PrimitiveArraySlice<ShortArray> {

    final override infix fun contentEquals(other: ArraySlice<ShortArray>) = contentEquals(other as ShortArraySlice)

    abstract infix fun contentEquals(other: ShortArraySlice): Boolean

    interface Delegate : ArraySlice.OutDelegate<ShortArray> {

        infix fun contentEquals(other: ShortArraySlice): Boolean
    }
}

fun ShortArraySlice(delegate: ShortArraySlice.Delegate): ShortArraySlice =
    object : ShortArraySlice.Delegate by delegate, ShortArraySlice() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

// For arrays

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

fun BooleanArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)
fun ByteArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)
fun CharArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)
fun DoubleArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)
fun FloatArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)
fun IntArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)
fun LongArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)
fun ShortArray.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

fun BooleanArray.asSlice(offset: Int, length: Int): BooleanArraySlice =
    asSliceTemplate(offset, length, size, ::arraySliceOf)

fun ByteArray.asSlice(offset: Int, length: Int): ByteArraySlice =
    asSliceTemplate(offset, length, size, ::arraySliceOf)

fun CharArray.asSlice(offset: Int, length: Int): CharArraySlice =
    asSliceTemplate(offset, length, size, ::arraySliceOf)

fun DoubleArray.asSlice(offset: Int, length: Int): DoubleArraySlice =
    asSliceTemplate(offset, length, size, ::arraySliceOf)

fun FloatArray.asSlice(offset: Int, length: Int): FloatArraySlice =
    asSliceTemplate(offset, length, size, ::arraySliceOf)

fun IntArray.asSlice(offset: Int, length: Int): IntArraySlice =
    asSliceTemplate(offset, length, size, ::arraySliceOf)

fun LongArray.asSlice(offset: Int, length: Int): LongArraySlice =
    asSliceTemplate(offset, length, size, ::arraySliceOf)

fun ShortArray.asSlice(offset: Int, length: Int): ShortArraySlice =
    asSliceTemplate(offset, length, size, ::arraySliceOf)

private inline fun <reified A, reified S> A.asSliceTemplate(
    off: Int, len: Int, arrSize: Int, sliceFac: (A, Int, Int) -> S
): S {
    checkIndexBounds(arrSize, off, len)
    return sliceFac(this, off, len)
}

// For array slices

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

fun BooleanArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)
fun ByteArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)
fun CharArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)
fun DoubleArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)
fun FloatArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)
fun IntArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)
fun LongArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)
fun ShortArraySlice.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

fun BooleanArraySlice.asSlice(offset: Int, length: Int) = asSliceTemplate(offset, length, ::arraySliceOf)
fun ByteArraySlice.asSlice(offset: Int, length: Int) = asSliceTemplate(offset, length, ::arraySliceOf)
fun CharArraySlice.asSlice(offset: Int, length: Int) = asSliceTemplate(offset, length, ::arraySliceOf)
fun DoubleArraySlice.asSlice(offset: Int, length: Int) = asSliceTemplate(offset, length, ::arraySliceOf)
fun FloatArraySlice.asSlice(offset: Int, length: Int) = asSliceTemplate(offset, length, ::arraySliceOf)
fun IntArraySlice.asSlice(offset: Int, length: Int) = asSliceTemplate(offset, length, ::arraySliceOf)
fun LongArraySlice.asSlice(offset: Int, length: Int) = asSliceTemplate(offset, length, ::arraySliceOf)
fun ShortArraySlice.asSlice(offset: Int, length: Int) = asSliceTemplate(offset, length, ::arraySliceOf)

private inline fun <reified A, reified S : PrimitiveArraySlice<A>> S.asSliceTemplate(
    off: Int, len: Int, sliceFac: A.(Int, Int) -> S
): S {
    checkIndexBounds(length, off, len)
    return sliceFac(array, offset + off, len)
}

// Implementations

private fun arraySliceOf(array: BooleanArray, offset: Int, length: Int) =
    BooleanArraySlice(BooleanArraySliceDelegateImpl(array, offset, length))
private fun arraySliceOf(array: ByteArray, offset: Int, length: Int) =
    ByteArraySlice(ByteArraySliceDelegateImpl(array, offset, length))
private fun arraySliceOf(array: CharArray, offset: Int, length: Int) =
    CharArraySlice(CharArraySliceDelegateImpl(array, offset, length))
private fun arraySliceOf(array: DoubleArray, offset: Int, length: Int) =
    DoubleArraySlice(DoubleArraySliceDelegateImpl(array, offset, length))
private fun arraySliceOf(array: FloatArray, offset: Int, length: Int) =
    FloatArraySlice(FloatArraySliceDelegateImpl(array, offset, length))
private fun arraySliceOf(array: IntArray, offset: Int, length: Int) =
    IntArraySlice(IntArraySliceDelegateImpl(array, offset, length))
private fun arraySliceOf(array: LongArray, offset: Int, length: Int) =
    LongArraySlice(LongArraySliceDelegateImpl(array, offset, length))
private fun arraySliceOf(array: ShortArray, offset: Int, length: Int) =
    ShortArraySlice(ShortArraySliceDelegateImpl(array, offset, length))

private class BooleanArraySliceDelegateImpl(array: BooleanArray, offset: Int, length: Int) : BooleanArraySlice.Delegate,
    ArraySliceImplBase<BooleanArray>(BooleanArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = array.sliceToNewArray(offset, length, BooleanArray::copyOfRange)

    override infix fun contentEquals(other: BooleanArraySlice): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

private class ByteArraySliceDelegateImpl(array: ByteArray, offset: Int, length: Int) : ByteArraySlice.Delegate,
    ArraySliceImplBase<ByteArray>(ByteArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = array.sliceToNewArray(offset, length, ByteArray::copyOfRange)

    override infix fun contentEquals(other: ByteArraySlice): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

private class CharArraySliceDelegateImpl(array: CharArray, offset: Int, length: Int) : CharArraySlice.Delegate,
    ArraySliceImplBase<CharArray>(CharArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = array.sliceToNewArray(offset, length, CharArray::copyOfRange)

    override infix fun contentEquals(other: CharArraySlice): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

private class DoubleArraySliceDelegateImpl(array: DoubleArray, offset: Int, length: Int) : DoubleArraySlice.Delegate,
    ArraySliceImplBase<DoubleArray>(DoubleArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = array.sliceToNewArray(offset, length, DoubleArray::copyOfRange)

    override infix fun contentEquals(other: DoubleArraySlice): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

private class FloatArraySliceDelegateImpl(array: FloatArray, offset: Int, length: Int) : FloatArraySlice.Delegate,
    ArraySliceImplBase<FloatArray>(FloatArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = array.sliceToNewArray(offset, length, FloatArray::copyOfRange)

    override infix fun contentEquals(other: FloatArraySlice): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

private class IntArraySliceDelegateImpl(array: IntArray, offset: Int, length: Int) : IntArraySlice.Delegate,
    ArraySliceImplBase<IntArray>(IntArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = array.sliceToNewArray(offset, length, IntArray::copyOfRange)

    override infix fun contentEquals(other: IntArraySlice): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

private class LongArraySliceDelegateImpl(array: LongArray, offset: Int, length: Int) : LongArraySlice.Delegate,
    ArraySliceImplBase<LongArray>(LongArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = array.sliceToNewArray(offset, length, LongArray::copyOfRange)

    override infix fun contentEquals(other: LongArraySlice): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

private class ShortArraySliceDelegateImpl(array: ShortArray, offset: Int, length: Int) : ShortArraySlice.Delegate,
    ArraySliceImplBase<ShortArray>(ShortArraySlice::class, array, offset, length) {
    override fun toSlicedArray() = array.sliceToNewArray(offset, length, ShortArray::copyOfRange)

    override infix fun contentEquals(other: ShortArraySlice): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

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

/** Generic array slice interface. */
abstract class GenericArraySlice<T> : ArraySlice.Typed<T, Array<T>> {

    final override fun contentEquals(other: ArraySlice<Array<T>>) = contentEquals(other as GenericArraySlice<*>)
    abstract infix fun contentEquals(other: GenericArraySlice<*>): Boolean

    final override fun copyInto(destination: ArraySlice.Typed<in T, Array<T>>): GenericArraySlice<in T> =
        copyInto(destination as GenericArraySlice<in T>)

    abstract fun copyInto(destination: GenericArraySlice<in T>): GenericArraySlice<in T>

    /** @return array index of [element]. */
    abstract fun arrayBinarySearch(element: T, comparator: Comparator<in T>): Int

    abstract class Comparable<T : kotlin.Comparable<T>> : ArraySlice.Typed.Comparable<T, Array<T>>,
        GenericArraySlice<T>()

    interface Delegate<T> : ArraySlice.OutDelegate.Typed<T, Array<T>> {

        infix fun contentEquals(other: GenericArraySlice<*>): Boolean {
            val merge = asIterable().asSequence() zip other.asIterable().asSequence()
            return merge.all { (a, b) -> a == b }
        }

        fun copyInto(destination: GenericArraySlice<in T>): GenericArraySlice<in T> =
            throw UnsupportedOperationException()

        /**
         * Degenerates to [arrayIndexOf] by default.
         *
         * @see GenericArraySlice.arrayBinarySearch
         */
        fun arrayBinarySearch(element: T, comparator: Comparator<in T>): Int = arrayIndexOf(element)

        interface Comparable<T : kotlin.Comparable<T>> : ArraySlice.OutDelegate.Typed.Comparable<T, Array<T>>,
            Delegate<T>
    }

    companion object {

        fun <T : kotlin.Comparable<T>> Comparable(delegate: Delegate.Comparable<T>): Comparable<T> =
            object : Delegate.Comparable<T> by delegate, Comparable<T>() {
                override operator fun equals(other: Any?) = delegate == other
                override fun hashCode() = delegate.hashCode()
                override fun toString() = delegate.toString()
            }
    }
}

fun <T> GenericArraySlice(delegate: GenericArraySlice.Delegate<T>): GenericArraySlice<T> =
    object : GenericArraySlice.Delegate<T> by delegate, GenericArraySlice<T>() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

fun <T> fromDelegate(delegate: GenericArraySlice.Delegate<T>) = GenericArraySlice(delegate)

fun <T : Comparable<T>> fromDelegate(delegate: GenericArraySlice.Delegate.Comparable<T>) =
    GenericArraySlice.Comparable(delegate)

// To primitive array slice

fun GenericArraySlice<out Boolean>.toBooleanArraySlice(): BooleanArraySlice = array.toBooleanArray().asSlice(offset, length)
fun GenericArraySlice<out Byte>.toByteArraySlice(): ByteArraySlice = array.toByteArray().asSlice(offset, length)
fun GenericArraySlice<out Char>.toCharArraySlice(): CharArraySlice = array.toCharArray().asSlice(offset, length)
fun GenericArraySlice<out Double>.toDoubleArraySlice(): DoubleArraySlice = array.toDoubleArray().asSlice(offset, length)
fun GenericArraySlice<out Float>.toFloatArraySlice(): FloatArraySlice = array.toFloatArray().asSlice(offset, length)
fun GenericArraySlice<out Int>.toIntArraySlice(): IntArraySlice = array.toIntArray().asSlice(offset, length)
fun GenericArraySlice<out Long>.toLongArraySlice(): LongArraySlice = array.toLongArray().asSlice(offset, length)
fun GenericArraySlice<out Short>.toShortArraySlice(): ShortArraySlice = array.toShortArray().asSlice(offset, length)

// Factories for arrays

fun <T> Array<T>.asSlice() = asSlice(0, size)
fun <T : Comparable<T>> Array<T>.asSlice() = asSlice(0, size)

fun <T> Array<T>.asSliceTo(to: Int) = asSliceFrom(0, to)
fun <T : Comparable<T>> Array<T>.asSliceTo(to: Int) = asSliceFrom(0, to)

@JvmOverloads
fun <T> Array<T>.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

@JvmOverloads
fun <T : Comparable<T>> Array<T>.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)

fun <T> Array<T>.asSlice(offset: Int, length: Int): GenericArraySlice<T> =
    asSliceTmpl(size, offset, length, ::GenericArraySliceDelegateImpl, ::fromDelegate)

fun <T : Comparable<T>> Array<T>.asSlice(offset: Int, length: Int): GenericArraySlice.Comparable<T> =
    asSliceTmpl(size, offset, length, GenericArraySliceDelegateImpl<*>::Comparable, ::fromDelegate)

// Factories for array slices

fun <T> GenericArraySlice<T>.asSlice(): GenericArraySlice<T> = this
fun <T : Comparable<T>> GenericArraySlice.Comparable<T>.asSlice(): GenericArraySlice.Comparable<T> = this

fun <T> GenericArraySlice<T>.asSliceTo(to: Int) = asSliceFrom(0, to)
fun <T : Comparable<T>> GenericArraySlice.Comparable<T>.asSliceTo(to: Int) = asSliceFrom(0, to)

@JvmOverloads
fun <T> GenericArraySlice<T>.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)

@JvmOverloads
fun <T : Comparable<T>> GenericArraySlice.Comparable<T>.asSliceFrom(from: Int, to: Int = length) =
    asSlice(from, to - from)

fun <T> GenericArraySlice<T>.asSlice(offset: Int, length: Int): GenericArraySlice<T> =
    asSliceTmpl(
        offset, length,
        ::GenericArraySliceDelegateImpl, ::fromDelegate
    )

fun <T : Comparable<T>> GenericArraySlice.Comparable<T>.asSlice(
    offset: Int, length: Int
): GenericArraySlice.Comparable<T> =
    asSliceTmpl(offset, length, GenericArraySliceDelegateImpl<*>::Comparable, ::fromDelegate)

// Implementations

private open class GenericArraySliceDelegateImpl<T>(
    array: Array<T>, offset: Int, length: Int
) : GenericArraySlice.Delegate<T>,
    ArraySliceDelegateImplBase<Array<T>>(GenericArraySlice::class, array, offset, length) {

    override fun toSlicedArray() = toSlicedArrayTmpl(Array<T>::copyOfRange)
    override infix fun contentEquals(other: GenericArraySlice<*>) = contentEqualsTmpl(other, Arrays::equals)
    override fun arrayIterator(arrayIndex: Int) = iteratorTmpl(arrayIndex, Array<out T>::get, Array<in T>::set)
    override fun copyInto(destination: GenericArraySlice<in T>): GenericArraySlice<in T> = copyIntoTmpl(destination)
    override fun fill(element: T) = fillTmpl(element, Array<in T>::fill)
    override fun arrayBinarySearch(element: T, comparator: Comparator<in T>) =
        arrayBinarySearchTmpl(element, comparator, Array<out T>::binarySearch)

    class Comparable<T : kotlin.Comparable<T>>(array: Array<T>, offset: Int, length: Int) :
        GenericArraySlice.Delegate.Comparable<T>, GenericArraySliceDelegateImpl<T>(array, offset, length) {

        override fun arrayBinarySearch(element: T) = arrayBinarySearchTmpl(element, Array<out T>::binarySearch)
        override fun sort() = sortTmpl(Array<out T>::sort)
    }
}

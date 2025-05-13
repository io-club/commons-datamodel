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
abstract class GenericArraySlice<T> : ArraySlice<Array<T>> {

    final override fun contentEquals(other: ArraySlice<Array<T>>) = contentEquals(other as GenericArraySlice)

    abstract infix fun contentEquals(other: GenericArraySlice<T>): Boolean

    interface Delegate<T> : ArraySlice.OutDelegate<Array<T>> {

        infix fun contentEquals(other: GenericArraySlice<T>): Boolean
    }
}

fun <T> GenericArraySlice(delegate: GenericArraySlice.Delegate<T>): GenericArraySlice<T> =
    object : GenericArraySlice.Delegate<T> by delegate, GenericArraySlice<T>() {
        override operator fun equals(other: Any?) = delegate == other
        override fun hashCode() = delegate.hashCode()
        override fun toString() = delegate.toString()
    }

fun <T> Array<T>.asSlice() = asSlice(0, size)
fun <T> Array<T>.asSliceTo(to: Int) = asSliceFrom(0, to)
fun <T> Array<T>.asSliceFrom(from: Int, to: Int = size) = asSlice(from, to - from)
fun <T> Array<T>.asSlice(offset: Int, length: Int): GenericArraySlice<T> {
    checkIndexBounds(this.size, offset, length)
    return GenericArraySlice(GenericArraySliceDelegateImpl(this, offset, length))
}

fun <T> GenericArraySlice<T>.asSlice(): GenericArraySlice<T> = this
fun <T> GenericArraySlice<T>.asSliceTo(to: Int) = asSliceFrom(0, to)
fun <T> GenericArraySlice<T>.asSliceFrom(from: Int, to: Int = length) = asSlice(from, to - from)
fun <T> GenericArraySlice<T>.asSlice(offset: Int, length: Int): GenericArraySlice<T> {
    checkIndexBounds(this.length, offset, length)
    return GenericArraySlice(GenericArraySliceDelegateImpl(array, this.offset + offset, length))
}

private class GenericArraySliceDelegateImpl<T>(
    array: Array<T>, offset: Int, length: Int
) : GenericArraySlice.Delegate<T>, ArraySliceImplBase<Array<T>>(GenericArraySlice::class, array, offset, length) {

    override fun toSlicedArray() = array.sliceToNewArray(offset, length, Array<T>::copyOfRange)

    override infix fun contentEquals(other: GenericArraySlice<T>): Boolean {
        val off1 = this.offset
        val off2 = other.offset
        return Arrays.equals(array, off1, off1 + length, other.array, off2, off2 + other.length)
    }

    override fun contentHashCode() = toSlicedArray().contentHashCode()
}

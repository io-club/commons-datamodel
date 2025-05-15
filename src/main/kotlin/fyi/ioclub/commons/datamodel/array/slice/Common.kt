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

import kotlin.reflect.KClass

internal inline fun <A> A.sliceToNewArray(
    off: Int,
    len: Int,
    copier: A.(Int, Int) -> A,
): A = copier(off, off + len)

internal abstract class ArraySliceImplBase<out A : Any>(
    private val implFor: KClass<*>,
    val array: A,
    val offset: Int,
    val length: Int,
) {

    final override operator fun equals(other: Any?) =
        this === other || other is ArraySlice<*> && toTriple() == other.toTriple()

    final override fun hashCode() = toTriple().hashCode()

    private fun toTriple() = Triple(array, offset, length)

    final override fun toString() = "${implFor.simpleName}(array=$array, offset=$offset, length=$length)"
}

internal inline fun <A : Any, reified D : ArraySlice.OutDelegate<A>, reified S : ArraySlice<A>> A.asSliceTmpl(
    arrSize: Int,
    off: Int,
    len: Int,
    delegateFromArray: (arr: A, off: Int, len: Int) -> D,
    sliceFromDelegate: (D) -> S,
): S {
    checkIndexBounds(arrSize, off, len)
    val delegate = delegateFromArray(this, off, len)
    return sliceFromDelegate(delegate)
}

internal inline fun <A : Any, reified D : ArraySlice.OutDelegate<A>, reified S : ArraySlice<A>> S.asSliceTmpl(
    off: Int,
    len: Int,
    delegateFromArray: (arr: A, off: Int, len: Int) -> D,
    sliceFromDelegate: (D) -> S,
): S = if (offset == off && length == len) this
else {
    checkIndexBounds(length, off, len)
    val delegate = delegateFromArray(array, offset + off, len)
    sliceFromDelegate(delegate)
}

internal fun checkIndexBounds(capacity: Int, offset: Int, length: Int) {
    if (offset < 0) throw IndexOutOfBoundsException(offset)
    (offset + length).let { if (it > capacity) throw IndexOutOfBoundsException(it) }
}

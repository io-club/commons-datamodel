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

sealed interface ArraySliceProtocol<A> {

    val array: A
    val offset: Int
    val length: Int

    fun sliced(): A
}

fun <A> ArraySliceProtocol<A>.toTriple(): Triple<A, Int, Int> = Triple(array, offset, length)

interface ArraySlice<T> : ArraySliceProtocol<Array<T>>

fun <T> arraySliceOf(array: Array<T>, offset: Int, length: Int): ArraySlice<T> = ArraySliceImpl(array, offset, length)
fun <T> arraySliceOf(array: Array<T>): ArraySlice<T> = arraySliceOf(array, 0, array.size)
fun <T> arraySliceOf(arraySlice: ArraySlice<T>): ArraySlice<T> = arraySlice
fun <T> arraySliceOf(arraySlice: ArraySlice<T>, offset: Int, length: Int): ArraySlice<T> =
    arraySliceOf(arraySlice.array, arraySlice.offset + offset, length)

private class ArraySliceImpl<T>(override val array: Array<T>, override val offset: Int, override val length: Int) :
    ArraySlice<T> {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, Array<T>::size, Array<T>::copyOfRange)
}

/*
 * Copyright © 2025 IO Club
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package fyi.ioclub.commons.array.slice

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

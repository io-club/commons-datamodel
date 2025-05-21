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

import fyi.ioclub.commons.datamodel.array.iterator.ArrayIterator
import kotlin.reflect.KClass

internal inline fun <A : Any, D : ArraySliceData<A>> D.toSlicedArrayTmpl(
    copyOfRange: A.(from: Int, to: Int) -> A,
): A {
    val (arr, off, len) = toTriple()
    return arr.copyOfRange(off, off + len)
}

internal open class ArraySliceDataImpl<out A : Any>(
    private val implFor: KClass<*>,
    override val array: A,
    override val offset: Int,
    override val length: Int,
) : ArraySliceData<A> {

    final override operator fun equals(other: Any?) =
        this === other || other is ArraySliceData<*> && toTriple() == other.toTriple()

    final override fun hashCode() = toTriple().hashCode()

    final override fun toString() = "${implFor.simpleName}(array=$array, offset=$offset, length=$length)"
}

internal abstract class ArraySliceDelegateImplBase<out A : Any>(
    implFor: KClass<*>, array: A, offset: Int, length: Int,
) : ArraySlice.OutDelegate<A>, ArraySliceDataImpl<A>(implFor, array, offset, length)

internal inline fun <E, A : Any, D : ArraySlice.OutDelegate<A>, reified S : ArraySlice.Typed<E, A>> A.asSliceTmpl(
    arrSize: Int,
    off: Int,
    len: Int,
    delegateFromArray: (arr: A, off: Int, len: Int) -> D,
    sliceFromDelegate: (D) -> S,
): S {
    checkInCapacity(arrSize, off, len)
    val delegate = delegateFromArray(this, off, len)
    return sliceFromDelegate(delegate)
}

internal inline fun <A : Any, D : ArraySlice.OutDelegate<A>, reified S : ArraySlice<A>> S.asSliceTmpl(
    off: Int,
    len: Int,
    delegateFromArray: (arr: A, off: Int, len: Int) -> D,
    sliceFromDelegate: (D) -> S,
): S = if (offset == off && length == len) this
else {
    checkInCapacity(length, off, len)
    val delegate = delegateFromArray(array, offset + off, len)
    sliceFromDelegate(delegate)
}

private fun checkInCapacity(capacity: Int, offset: Int, length: Int) {
    if (offset < 0) throw IndexOutOfBoundsException(offset)
    (offset + length).let { if (it > capacity) throw IndexOutOfBoundsException(it) }
}

internal fun <E, A : Any, D : ArraySliceData.Typed<E, A>> D.contentEqualsDefaultTmpl(other: D): Boolean {
    val merge = asIterable().asSequence() zip other.asIterable().asSequence()
    return merge.all { (a, b) -> a == b }
}

internal inline fun <A : Any, D : ArraySliceData<A>> D.contentEqualsTmpl(
    other: D, arrEquals: ArrRangeEquals<A>
): Boolean = arrEquals(
    this.array, this.offset, this.endArrayIndexExclusive,
    other.array, other.offset, other.endArrayIndexExclusive,
)

internal inline fun <E, A : Any, D : ArraySliceData<A>> D.iteratorTmpl(
    arrIdx: Int, crossinline arrGet: ArrGet<E, A>, crossinline arrSet: ArrSet<E, A>
): ArrayIterator<E> = object : ArrayIterator<E> {
    private val endExclusive = endArrayIndexExclusive

    private var currArrIdx = arrIdx
    private var lastRetArrIdx = -1

    override fun next(): E {
        val i = currArrIdx
        if (i >= endExclusive) throw NoSuchElementException()
        currArrIdx = i + 1
        return getAndUpdateLastRet(i)
    }

    override fun hasNext() = currArrIdx < endExclusive
    override fun hasPrevious() = currArrIdx > offset

    override fun previous(): E {
        val i = currArrIdx - 1
        if (i < 0) throw NoSuchElementException()
        currArrIdx = i
        return getAndUpdateLastRet(i)
    }

    private fun getAndUpdateLastRet(i: Int): E {
        val e = array.arrGet(i)
        lastRetArrIdx = i
        return e
    }

    override fun nextIndex() = currArrIdx
    override fun previousIndex() = currArrIdx - 1

    override fun set(element: E) {
        check(lastRetArrIdx != -1) { "Neither next nor previous has not been called yet" }
        array.arrSet(lastRetArrIdx, element)
    }
}

internal fun <A : Any, OD : ArraySliceData<A>, D : OD> OD.copyIntoTmpl(dst: D): D {
    val (srcArr, srcOff, srcLen) = toTriple()
    val (dstArr, dstOff, dstLen) = dst.toTriple()
    if (dstLen < srcLen) throw IndexOutOfBoundsException(srcLen)
    System.arraycopy(srcArr, srcOff, dstArr, dstOff, srcLen)
    return dst
}

internal inline fun <E, A : Any, D : ArraySliceData<A>> D.fillTmpl(
    e: E, fill: ArrFill<E, A>
): Unit = array.fill(e, offset, endArrayIndexExclusive)

internal inline fun <E, A : Any, D : ArraySliceData<A>> D.arrayBinarySearchTmpl(
    e: E, cmp: Comparator<in E>, arrBinSearch: CmpArrBinSearch<E, A>
): Int = array.arrBinSearch(e, cmp, offset, endArrayIndexExclusive)

internal inline fun <E, A : Any, D : ArraySliceData<A>> D.arrayBinarySearchTmpl(
    e: E, arrBinSearch: GenArrBinSearch<E, A>
): Int = array.arrBinSearch(e, offset, endArrayIndexExclusive)

internal inline fun <A : Any, D : ArraySliceData<A>> D.sortTmpl(arrSort: ArrSort<A>): Unit =
    array.arrSort()

//private typealias EEquals<E1, E2> = (E1, E2) -> Unit
private typealias ArrRangeEquals<A> = (a: A, aFrom: Int, aTo: Int, b: A, bFrom: Int, bTo: Int) -> Boolean
private typealias ArrGet<E, A> = A.(i: Int) -> E
private typealias ArrSet<E, A> = A.(i: Int, v: E) -> Unit
private typealias GenArrBinSearch<E, A> = A.(e: E, from: Int, to: Int) -> Int
private typealias CmpArrBinSearch<E, A> = A.(e: E, cmp: Comparator<in E>, from: Int, to: Int) -> Int
private typealias ArrFill<E, A> = A.(e: E, from: Int, to: Int) -> Unit
private typealias ArrSort<A> = A.() -> Unit

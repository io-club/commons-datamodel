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
import org.jetbrains.annotations.Range

sealed interface ArraySliceData<out A : Any> {

    val array: A

    val offset: @Range(from = 0, to = Int.MAX_VALUE.toLong()) Int

    val length: @Range(from = 0, to = Int.MAX_VALUE.toLong()) Int

    sealed interface Typed<E, out A : Any> : ArraySliceData<A> {

        operator fun iterator(): Iterator<E>
    }
}

fun <A : Any> ArraySliceData<A>.toTriple(): Triple<A, Int, Int> = Triple(array, offset, length)
val <A : Any> ArraySliceData<A>.arrayIndices: IntRange get() = IntRange(offset, endArrayIndexInclusive)
val <A : Any> ArraySliceData<A>.endArrayIndexInclusive get() = endArrayIndexExclusive - 1
val <A : Any> ArraySliceData<A>.endArrayIndexExclusive get() = offset + length
fun <E, A : Any> ArraySliceData.Typed<E, A>.asIterable(): Iterable<E> = asCollection()
fun <E, A : Any> ArraySliceData.Typed<E, A>.asCollection(): Collection<E> =
    object : Collection<E>, AbstractCollection<E>() {
        override val size get() = length
        override operator fun iterator() = this@asCollection.iterator()
    }

/**
 * General array slice base interface.
 * Compatible with generic arrays [Array]
 * and primitive type arrays like [ByteArray].
 *
 * @param A subtype of [Any] which interprets to [Object] for [System.arraycopy].
 * In implementations of this sealed interface,
 * it must be [Array] or one of the primitive type array types like [ByteArray].
 *
 * @see GenericArraySliceComparable
 * @see PrimitiveArraySlice
 */
sealed interface ArraySlice<out A : Any> : ArraySliceData<A> {

    /**
     * Creates a new array of data sliced from [array], from position [offset] and of length [length].
     *
     * @return the result array
     */
    fun toSlicedArray(): A

    /**
     * This method is only implemented by the abstract classes:
     * [GenericArraySliceComparable] and subclasses of [PrimitiveArraySlice].
     *
     * Currently, custom class must not implement this method.
     * Instead, it may implement `contentEquals` for specific subtype of [ArraySlice]
     * , for example, `GenericArraySlice.contentEquals(GenericArraySlice<T>)`.
     * Or you may implement `contentEquals` of subclasses of [OutDelegate]
     * and use from-delegate factories
     * to indirectly implement the specific-typed `contentEquals` of [ArraySlice] subclasses.
     */
    fun contentEquals(other: ArraySlice<@UnsafeVariance A>): Boolean

    fun contentHashCode(): Int

    fun contentToString(): String

    fun contentDeepHashCode(): Int

    fun contentDeepToString(): String

    sealed interface Typed<E, out A : Any> : ArraySliceData.Typed<E, A>, ArraySlice<A> {

        /**
         * Returns an iterator over [array].
         *
         * Notice that here [ArrayIterator.nextIndex] and [ArrayIterator.previousIndex]
         * return indices of [array] and where [offset] added.
         */
        fun arrayIterator(): ArrayIterator<E>

        fun arrayIterator(arrayIndex: Int): ArrayIterator<E>

        fun arrayIndexOf(element: E): Int

        fun lastArrayIndexOf(element: E): Int

        fun copyInto(destination: Typed<in E, @UnsafeVariance A>): Typed<*, *>

        fun fill(element: E)

        sealed interface Comparable<E : kotlin.Comparable<E>, out A : Any> : Typed<E, A> {

            /** @return array index of [element]. */
            fun arrayBinarySearch(element: E): Int

            fun sort()
        }
    }

    /**
     * [OutDelegate] does not have any method as following described:
     *
     * Method has parameter of supertype `I`,
     * however subclasses should have methods of same function
     * but for parameter of subtype `O: I` making it impossible
     * to override method in superclass.
     *
     * Described method in [ArraySlice] is [ArraySlice.contentEquals].
     * Subclasses like [GenericArraySlice] implement it already
     * and custom [ArraySlice] implementations must not implement it,
     * making them abstract subclasses instead of interfaces.
     *
     * Subinterfaces of [OutDelegate], as nested interface in subclasses of [ArraySlice],
     * have `contentEquals` for parameter of type of themselves.
     */
    sealed interface OutDelegate<out A : Any> : ArraySliceData<A> {

        /** @see ArraySlice.toSlicedArray */
        fun toSlicedArray(): A

        /** @see ArraySlice.contentHashCode */
        fun contentHashCode(): Int

        /** @see ArraySlice.contentToString */
        fun contentToString(): String = "Content of $this"

        /** @see ArraySlice.contentDeepHashCode */
        fun contentDeepHashCode(): Int = throw UnsupportedOperationException()

        /** @see ArraySlice.contentDeepToString */
        fun contentDeepToString(): String = "Deep of ${contentToString()}"

        /**
         * Here, like in [OutDelegate], not only [ArraySlice.contentEquals],
         * but also [ArraySlice.Typed.copyInto] is not.
         */
        sealed interface Typed<E, out A : Any> : ArraySliceData.Typed<E, A>, OutDelegate<A> {

            override fun contentHashCode() = asIterable().fold(1) { rsl, e -> 31 * rsl + e.hashCode() }
            override fun contentToString() = asIterable().joinToString(separator = ", ", prefix = "[", postfix = "]")

            override fun contentDeepHashCode() = asIterable().fold(1) { rsl, e ->
                31 * rsl + e.run {
                    when (this) {
                        is ArraySlice<*> -> contentDeepHashCode()
                        is Array<*> -> contentDeepHashCode()
                        is BooleanArray -> contentHashCode()
                        is ByteArray -> contentHashCode()
                        is CharArray -> contentHashCode()
                        is DoubleArray -> contentHashCode()
                        is FloatArray -> contentHashCode()
                        is IntArray -> contentHashCode()
                        is LongArray -> contentHashCode()
                        is ShortArray -> contentHashCode()
                        else -> hashCode()
                    }
                }
            }

            override fun contentDeepToString() =
                asIterable().joinToString(separator = ", ", prefix = "[", postfix = "]") { e ->
                    e.run {
                        when (this) {
                            is ArraySlice<*> -> contentDeepToString()
                            is Array<*> -> contentDeepToString()
                            is BooleanArray -> contentToString()
                            is ByteArray -> contentToString()
                            is CharArray -> contentToString()
                            is DoubleArray -> contentToString()
                            is FloatArray -> contentToString()
                            is IntArray -> contentToString()
                            is LongArray -> contentToString()
                            is ShortArray -> contentToString()
                            else -> toString()
                        }
                    }
                }

            override operator fun iterator(): Iterator<E> = arrayIterator()

            /** @see ArraySlice.Typed.arrayIterator */
            fun arrayIterator(): ArrayIterator<E> = arrayIterator(offset)

            /** @see ArraySlice.Typed.arrayIterator */
            fun arrayIterator(arrayIndex: Int): ArrayIterator<E>

            /** @see ArraySlice.Typed.arrayIndexOf */
            fun arrayIndexOf(element: E): Int {
                val itr = arrayIterator()
                while (itr.hasNext()) if (element == itr.next()) return itr.previousIndex()
                return -1
            }

            /** @see ArraySlice.Typed.lastArrayIndexOf */
            fun lastArrayIndexOf(element: E): Int {
                val itr = arrayIterator(endArrayIndexExclusive)
                while (itr.hasPrevious()) if (element == itr.previous()) return itr.nextIndex()
                return -1
            }

            /** @see ArraySlice.Typed.fill */
            fun fill(element: E) = with(arrayIterator()) { while (hasNext()) set(element) }

            sealed interface Comparable<E : kotlin.Comparable<E>, out A : Any> : Typed<E, A> {

                /**
                 * Degenerates to [arrayIndexOf] by default.
                 *
                 * @see ArraySlice.Typed.Comparable.arrayBinarySearch
                 */
                fun arrayBinarySearch(element: E): Int = arrayIndexOf(element)

                /** @see ArraySlice.Typed.Comparable.sort */
                fun sort(): Unit = throw UnsupportedOperationException()
            }
        }
    }
}

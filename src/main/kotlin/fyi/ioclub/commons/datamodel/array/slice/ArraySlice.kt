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

import org.jetbrains.annotations.Range

/**
 * General array slice base interface.
 * Compatible with generic arrays [Array]
 * and primitive type arrays like [ByteArray].
 *
 * @param A subtype of [Any] which interprets to [Object] for [System.arraycopy].
 * In implementations of this sealed interface,
 * it must be [Array] or one of the primitive type array types like [ByteArray].
 *
 * @see GenericArraySlice
 * @see PrimitiveArraySlice
 */
sealed interface ArraySlice<out A: Any> {

    val array: A

    val offset: @Range(from = 0, to = Int.MAX_VALUE.toLong()) Int

    val length: @Range(from = 0, to = Int.MAX_VALUE.toLong()) Int

    /**
     * Creates a new array of data sliced from [array], from position [offset] and of length [length].
     *
     * @return the result array
     */
    fun toSlicedArray(): A

    /**
     * This method is only implemented by the abstract classes:
     * [GenericArraySlice] and subclasses of [PrimitiveArraySlice].
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
     * Therefore, we need an interface
     *
     * Subinterfaces of [OutDelegate], as nested interface in subclasses of [ArraySlice],
     * have `contentEquals` for parameter of type of themselves.
     */
    sealed interface OutDelegate<out A>{

        val array: A

        val offset: @Range(from = 0, to = Int.MAX_VALUE.toLong()) Int

        val length: @Range(from = 0, to = Int.MAX_VALUE.toLong()) Int

        fun toSlicedArray(): A

        fun contentHashCode(): Int
    }
}

fun <A : Any> ArraySlice<A>.toTriple(): Triple<A, Int, Int> = Triple(array, offset, length)

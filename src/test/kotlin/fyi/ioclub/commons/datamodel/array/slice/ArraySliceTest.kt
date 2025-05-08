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

import fyi.ioclub.commons.datamodel.array.slice.easy.get
import java.nio.ByteBuffer

fun testByteArraySlice() {
    val arr = "array".encodeToByteArray()
    println(arr[1, 3].sliced().decodeToString())
}

fun testBooleanArraySlice() {
    val arr = booleanArrayOf(true, false, true)
    println(arr[1, 2].sliced().toList())
}

fun testShortArraySlice() {
    val arr = shortArrayOf(1, 2, 3)
    println(arr[1, 2].sliced().toList())
}

fun testIntArraySlice() {
    val arr = intArrayOf(1, 2, 3)
    println(arr[1, 2].sliced().toList())
}

fun testLongArraySlice() {
    val arr = longArrayOf(1, 2, 3)
    println(arr[1, 2].sliced().toList())
}

fun testCharArraySlice() {
    val arr = charArrayOf('a', 'b', 'c')
    println(arr[1, 2].sliced().toList())
}

fun testDoubleArraySlice() {
    val arr = doubleArrayOf(1.0, 2.0, 3.0)
    println(arr[1, 2].sliced().toList())
}

fun testFloatArraySlice() {
    val arr = floatArrayOf(1.0F, 2.0F, 3.0F)
    println(arr[1, 2].sliced().toList())
}

fun testMisc() {
    val buf = ByteBuffer.allocate(5)
    buf.put("array".encodeToByteArray())
    val slice = buf.getArraySlice()
    val (arr, off, len) = slice.toTriple()
    println("Array:\t${arr.decodeToString()}")
    println("Offset:\t$off")
    println("Length:\t$len")
}

fun main() {
    testByteArraySlice()
    testBooleanArraySlice()
    testShortArraySlice()
    testIntArraySlice()
    testLongArraySlice()
    testCharArraySlice()
    testDoubleArraySlice()
    testFloatArraySlice()
    testMisc()
}

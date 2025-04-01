/*
 * Copyright © 2025 IOClub
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package fyi.ioclub.commons.array.slice

import fyi.ioclub.commons.array.slice.simplified.get
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

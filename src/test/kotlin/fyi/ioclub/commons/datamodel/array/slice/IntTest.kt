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

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import fyi.ioclub.commons.datamodel.array.slice.IntArraySlice as TestArraySlice
import fyi.ioclub.commons.datamodel.array.slice.toIntArraySlice as toPrimitiveArraySlice

private fun createArray3() = intArrayOf(0, 1, 2)

class IntTest {

    @Test
    fun test() {
        val a3 = createArray3()
        val s3 = a3.asSlice(0, a3.size)
        assertEquals(s3, a3.asSliceTo(a3.size))
        assertEquals(s3, a3.asSliceFrom(0))
        assertEquals(s3, a3.asSlice())

        val s2 = a3.asSlice(1, 2)
        assertEquals(s2, s3.asSliceFrom(1).asSliceTo(s2.length))
        assertEquals(s2, s3.asSliceFrom(1))

        val a2 = a3.copyOfRange(1, 1 + 2)
        assertContentEquals(a2, s2.toSlicedArray())
        assertTrue(a2 contentEquals s2.toSlicedArray())
        assertEquals(a2.contentHashCode(), s2.contentHashCode())

        assertTrue(s2 contentEquals a2.asSlice())

        val d = object : TestArraySlice.Delegate {
            override val array = s2.array
            override val offset = s2.offset
            override val length = s2.length
            override fun toString() = "CustomArraySlice"
            override fun toSlicedArray() = throw UnsupportedOperationException()
            override fun arrayIterator(arrayIndex: Int) = TODO("Not yet implemented")
        }
        val c = TestArraySlice(d)
        assertEquals(s2.toTriple(), c.toTriple())

        println(c)
        println(s2)

        assertTrue(s3.toTypedArraySlice().toPrimitiveArraySlice() contentEquals s3)

        assertTrue(
            (s2.arrayIterator().asSequence() zip s3.arrayIterator(s3.offset + 1).asSequence()).all { (a, b) -> a == b })

        val a31 = a3[1]
        val a32 = a3[2]
        a3[2] = a31
        assertEquals(1, s3.arrayIndexOf(a31))
        assertEquals(2, s3.lastArrayIndexOf(a31))
        a3[2] = a32
        assertEquals(s3.arrayIndexOf(a31), s3.arrayBinarySearch(a31))

        run {
            val temp = a3[2]
            a3[2] = a3[0]
            a3[0] = temp
        }
        println("Not sorted: ${s3.contentToString()}")
        s3.sort()
        println("Sorted: ${s3.contentToString()}")

        val sc = s3.toSlicedArray().asSlice()
        s3.fill(a31)
        assertTrue(s3.asIterable().all(a31::equals))
        s3.copyInto(sc)
        assertTrue(s3 contentEquals sc)
    }
}

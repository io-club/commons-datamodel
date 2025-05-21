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
import fyi.ioclub.commons.datamodel.array.slice.CharArraySlice as TestArraySlice

private fun createArray3() = charArrayOf('0', '1', '2')

class CharTest {

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

        println(s2)
        println(c)
    }
}

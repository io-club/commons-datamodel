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

package fyi.ioclub.commons.datamodel.array.concat

import fyi.ioclub.commons.datamodel.array.slice.asSlice
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

private const val E1 = true
private const val E2 = false
private const val E3 = true

class BooleanTest {

    @Test
    fun testConcat2() {
        val a1 = booleanArrayOf(E1).asSlice()
        val a2 = booleanArrayOf(E2).asSlice()
        val expected = booleanArrayOf(E1, E2)
        assertContentEquals(expected, concat(a1, a2))
        assertContentEquals(expected, a1 concat a2)
    }

    @Test
    fun testConcatN() {
        val a1 = booleanArrayOf(E1).asSlice()
        val a2 = booleanArrayOf(E2).asSlice()
        val a3 = booleanArrayOf(E3).asSlice()
        val expected = booleanArrayOf(E1, E2, E3)
        assertContentEquals(expected, concat(a1, a2, a3))
        assertContentEquals(expected, concat(listOf(a1, a2, a3)))
    }
}

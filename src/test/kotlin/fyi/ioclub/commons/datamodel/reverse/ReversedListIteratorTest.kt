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

package fyi.ioclub.commons.datamodel.reverse

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReversedListIteratorTest {

    @Test
    fun testIterateInReverse() {
        val li = mutableListOf(1, 2, 3)
        val itr = (li as List<Int>).listIterator(li.size).iterateInReverse()
        assertEquals(li.asReversed(), itr.asSequence().toList())

        val itrM = li.listIterator(li.size).iterateInReverse()
        val c = li.toList()
        with(itrM) {
            Iterable { itrM }.forEachIndexed { i, _ ->
                println("Prev: ${previousIndex()}   Next: ${nextIndex()}")
                set(c[i])
            }
        }
        assertEquals(c.asReversed(), li)
    }
}

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

package fyi.ioclub.commons.datamodel.link.mono

import fyi.ioclub.commons.datamodel.container.Container
import fyi.ioclub.commons.datamodel.container.getValue
import org.junit.jupiter.api.Test

class CursorTest {

    @Test
    fun testCursorOf() {
        val c = cursorOf<Int>()
        c.item = monoLinkedNodeOf(1, MonoLinkedBreaker)
        println(c.item)
    }

    @Test
    fun followIteration() {
        val c = cursorOf(monoLinkedNodeOf(1, MonoLinkedBreaker))
        println(c.item)
        c.followIteration().next()
        println(c.item)
    }

    @Test
    fun followAppending() {
        val c = cursorOf(Container.of(1).monoLinkToBreaker())
        val n by c
        println(n.item)
        val adder = c.followAddingNext()
        adder.addNext(Container.of(2))
        println(n.item)
    }
}

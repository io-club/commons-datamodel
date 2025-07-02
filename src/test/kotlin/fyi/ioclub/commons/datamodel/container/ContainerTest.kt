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

package fyi.ioclub.commons.datamodel.container

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertSame

class ContainerTest {

    @Test
    fun testImmutable() {
        val a0 by Container.of(0)
        assertEquals(0, a0)
        val backend = 0
        val c1 = Container.ofProperty { _, _ -> backend.hashCode() }
        val a1 by c1
        assertEquals(backend.hashCode(), a1)
        val a2 by Container.ofGetter { 0 }
        assertEquals(0, a2)
        val a3 by Container.ofInitializer { 0 }
        assertEquals(0, a3)
        assert(a3 == 0)
        val ce = Container.empty<Int>()
        assertSame(Container.Empty, ce)
    }

    @Test
    fun testMutable() {
        var backend = 0
        var a2 by Container.Mutable.ofGetterSetter({ backend + 1 }, { v -> backend = v + 1 })
        a2 = 1
        assertEquals(3, a2)

        var a3 by Container.Mutable.ofInitializer { 0 }
        assertEquals(0, a3)
        a3 = 1
        assertEquals(1, a3)

        val ce = Container.Mutable.empty<Int>()
        var ae by ce
        assertEquals(Container.Mutable.NAME_EMPTY, ce.toString())
        ae = 0
        assertEquals(0, ae)
        assertNotEquals(Container.Mutable.NAME_EMPTY, ce.toString())
    }
}

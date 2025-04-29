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
import fyi.ioclub.commons.datamodel.container.setValue
import org.junit.jupiter.api.Test

class ContainerTest {

    @Test
    fun testEmpty() {
        val e = Container.empty<Int>()
        assert(e === Container.Empty)
    }

    @Test
    fun testOf() {
        val a0 by Container.of(0)
        assert(a0 == 0)
        val c1 = Container.ofProperty { thisRef, _ -> thisRef.hashCode() }
        val a1 by c1
        assert(a1 == c1.hashCode())
        val a2 by Container.ofGetter { 0 }
        assert(a2 == 0)
        val a3 by Container.ofLazy { 0 }
        assert(a3 == 0)
    }

    @Test
    fun testMutableOf() {
        var backend = 0
        var a2 by Container.Mutable.ofGetterSetter({ backend + 1 }, { v -> backend = v + 1 })
        a2 = 1
        @Suppress("KotlinConstantConditions")
        assert(a2 == 3)
    }
}
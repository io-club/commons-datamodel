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
import fyi.ioclub.commons.datamodel.link.mono.MonoLinkedBreaker
import fyi.ioclub.commons.datamodel.link.mono.MonoLinkedNode
import fyi.ioclub.commons.datamodel.link.mono.linkNext
import fyi.ioclub.commons.datamodel.link.mono.monoLinkTo
import fyi.ioclub.commons.datamodel.link.mono.toIterable
import fyi.ioclub.commons.datamodel.link.mono.toMutable
import fyi.ioclub.commons.datamodel.link.mono.toMutableDeep
import org.junit.jupiter.api.Test

internal fun <T> monoLinkedNodeOf(item: T, next: MonoLinkedNode<T>? = null) = Container.of(item).monoLinkTo(next)

class MonoLinkedNodeTest {

    private lateinit var n0: MonoLinkedNode<Int>
    private lateinit var n1: MonoLinkedNode<Int>
    private lateinit var n2: MonoLinkedNode<Int>

    @Test
    fun testMonoLinkedNodeOf() {
        n2 = monoLinkedNodeOf(2)
        n1 = monoLinkedNodeOf(1)
        n0 = monoLinkedNodeOf(0, n1)
        println("Created $n0, $n1 and $n2")
    }

    @Test
    fun linkNext() {
        testMonoLinkedNodeOf()
        n1.linkNext(monoLinkedNodeOf(2))
        println("$n1 now has $n2 as next")
    }

    @Test
    fun toMutable() {
        testMonoLinkedNodeOf()
        n0.toMutable() as MonoLinkedNode.LinkMutable<*>
    }

    @Test
    fun toMutableDeep() {
        linkNext()
        n0.toMutableDeep()
        (n0.next.next.next.next.next as MonoLinkedNode.LinkMutable<*>).next = MonoLinkedBreaker
        println(n0.toIterable().toList())
    }
}
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
import fyi.ioclub.commons.datamodel.link.mono.reversedToMonoLinkedNodes
import fyi.ioclub.commons.datamodel.link.mono.toIterable
import fyi.ioclub.commons.datamodel.link.mono.toMonoLinkedNodes
import org.junit.jupiter.api.Test

class FromCollectionsTest {

    @Test
    fun toMonoLinkedNodes() {
        val l = listOf(0, 1, 2, 3).map(Container.Companion::of)
        println(l.toMonoLinkedNodes().first().toIterable().toList())
        println((l as Collection<Container<Int>>).toMonoLinkedNodes().first().toIterable().toList())
        println((l as Iterable<Container<Int>>).toMonoLinkedNodes().first().toIterable().toList())
    }

    @Test
    fun reversedToMonoLinkedNodes() {
        val l = listOf(0, 1, 2, 3).map(Container.Companion::of)
        println(l.reversedToMonoLinkedNodes().last().toIterable().toList())
        println((l as Collection<Container<Int>>).reversedToMonoLinkedNodes().last().toIterable().toList())
        println((l as Iterable<Container<Int>>).reversedToMonoLinkedNodes().last().toIterable().toList())
    }
}
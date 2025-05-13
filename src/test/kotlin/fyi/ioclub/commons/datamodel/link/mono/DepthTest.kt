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

class DepthTest {

    @Test
    fun listenDepth() {
        val depthContainer = Container.Mutable.of(0)
        val depthListeningNode = Container.of("self").monoLinkTo(null).listenDepth(depthContainer)
        val maxDepth = 4
        val depth by depthContainer
        println(depthListeningNode.toIterable { depth < maxDepth }.toList())
    }
}

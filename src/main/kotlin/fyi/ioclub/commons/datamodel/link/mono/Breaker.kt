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

/** Multipurpose placeholder. The reason why [MonoLinkedNode.next] not nullable. */
object MonoLinkedBreaker : MonoLinkedNode<Nothing> {

    /** Delegates to [Container.Empty].*/
    override val item get() = Container.Empty.item

    override val next get() = throw NoSuchElementException()

    override fun toString() = "${this::class.simpleName}"
}

val MonoLinkedNode<*>.isBreaker get() = this === MonoLinkedBreaker
val MonoLinkedNode<*>.isNotBreaker get() = this !== MonoLinkedBreaker

fun <T> Container<T>.monoLinkToBreaker() = monoLinkTo(MonoLinkedBreaker as MonoLinkedNode<T>)

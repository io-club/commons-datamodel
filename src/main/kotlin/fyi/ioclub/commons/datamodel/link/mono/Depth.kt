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
import org.jetbrains.annotations.Range

private typealias Depth = @Range(from = 0, to = Int.MAX_VALUE.toLong()) Int

fun <T> MonoLinkedNode<T>.listenDepth(depthContainer: Container.Mutable<Depth>): MonoLinkedNode<T> =
    if (this is MonoLinkedNode.LinkMutable) listenDepth(depthContainer) else DepthListeningDelegate(
        this,
        depthContainer,
    )

fun <T> MonoLinkedNode.LinkMutable<T>.listenDepth(depthContainer: Container.Mutable<Depth>): MonoLinkedNode.LinkMutable<T> =
    LinkMutableDepthListeningDelegate(this, depthContainer)

private class LinkMutableDepthListeningDelegate<T>(
    override val node: MonoLinkedNode.LinkMutable<T>,
    depthContainer: Container.Mutable<Depth>,
) : MonoLinkedNode.LinkMutable<T>, DepthListeningDelegate<T>(node, depthContainer) {

    override var next: MonoLinkedNode<T>
        get() = super.next
        set(value) = value.let(node::next::set)
}

private open class DepthListeningDelegate<T>(
    protected open val node: MonoLinkedNode<T>,
    private val depthContainer: Container.Mutable<Depth>,
) : MonoLinkedNode<T> {

    final override val item get() = node.item

    override val next: MonoLinkedNode<T>
        get() {
            depthContainer.item++
            val next = node.next
            return next.listenDepth(depthContainer)
        }
}

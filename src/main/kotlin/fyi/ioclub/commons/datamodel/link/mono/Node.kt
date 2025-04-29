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

interface MonoLinkedNode<out T> : Container<T> {

    val next: MonoLinkedNode<T>

    interface LinkMutable<T> : MonoLinkedNode<T> {

        override var next: MonoLinkedNode<T>
    }
}

/** @param next if `null` then link to node itself. */
fun <T> Container<T>.monoLinkTo(next: MonoLinkedNode<T>?): MonoLinkedNode.LinkMutable<T> =
    object : MonoLinkedNode.LinkMutable<T>, MLNodeImplBase<T>(this) {
        override var next: MonoLinkedNode<T> = next ?: this
    }

/** @param nextContainer container of next node. */
fun <T> Container<T>.monoLinkTo(nextContainer: Container.Mutable<MonoLinkedNode<T>>): MonoLinkedNode.LinkMutable<T> =
    object : MonoLinkedNode.LinkMutable<T>, MLNodeImplBase<T>(this) {
        override var next by nextContainer
    }

/** @param nextContainer container of next node. */
fun <T> Container<T>.monoLinkTo(nextContainer: Container<MonoLinkedNode<T>>): MonoLinkedNode<T> =
    object : MonoLinkedNode<T>, MLNodeImplBase<T>(this) {
        override val next by nextContainer
    }

private abstract class MLNodeImplBase<out T>(container: Container<T>) : MonoLinkedNode<T> {
    final override val item by container
}

/**
 * Try to link [other] as a [MonoLinkedNode.LinkMutable].
 *
 * @return next node [other].
 */
fun <T> MonoLinkedNode<T>.linkNext(other: MonoLinkedNode<T>): MonoLinkedNode<T> =
    if (this is MonoLinkedNode.LinkMutable) linkNext(other)
    else throw IllegalStateException("Failed to extend: node link immutable")

fun <T> MonoLinkedNode<T>.linkNext(other: MonoLinkedNode.LinkMutable<T>) =
    linkNext(other as MonoLinkedNode<T>) as MonoLinkedNode.LinkMutable

/** @return next node [other]. */
fun <T> MonoLinkedNode.LinkMutable<T>.linkNext(other: MonoLinkedNode<T>): MonoLinkedNode<T> = other.also(::next::set)
fun <T> MonoLinkedNode.LinkMutable<T>.linkNext(other: MonoLinkedNode.LinkMutable<T>) =
    linkNext(other as MonoLinkedNode<T>) as MonoLinkedNode.LinkMutable

/**
 * @param this immutable or mutable node. Note that [MonoLinkedBreaker] is and must be always immutable.
 * @return [MonoLinkedNode.LinkMutable]
 * @throws IllegalArgumentException if [this] is [MonoLinkedBreaker].
 */
fun <T> MonoLinkedNode<T>.toMutable(): MonoLinkedNode.LinkMutable<T> = this as? MonoLinkedNode.LinkMutable
    ?: if (this === MonoLinkedBreaker) throw IllegalArgumentException("$this must not be mutable")
    else let(::MonoLinkedNodeLinkMutableDelegate)

private class MonoLinkedNodeLinkMutableDelegate<T>(private val immutable: MonoLinkedNode<T>) :
    MonoLinkedNode.LinkMutable<T>, MonoLinkedNode<T> by immutable {

    override var next: MonoLinkedNode<T>
        get() = _next ?: immutable.next
        set(next) = next.let(::_next::set)

    private var _next: MonoLinkedNode<T>? = null
}

/**
 * Convert node and next all ones to mutable ones while [hasNext].
 *
 * Recursion stops at [MonoLinkedBreaker] by default.
 * Also, recursion stops when [MonoLinkedNode.next] referentially equals to [this]
 * to prevent simple infinite recursion.
 *
 * May use [listenDepth]
 * for recursion depth limitation.
 */
fun <T> MonoLinkedNode<T>.toMutableDeep(hasNext: (MonoLinkedNode<T>).() -> Boolean = MonoLinkedNode<*>::isNotBreaker): MonoLinkedNode<T> {
    if (!hasNext()) return this
    val mutable = toMutable()
    val next = next
    mutable.next = if (next === this) mutable else next.toMutableDeep()
    return mutable
}

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

typealias MonoLinkedNodeCursor<T> = Container.Mutable<MonoLinkedNode<T>>

/** Create a mutable cursor of linked node. */
fun <T> cursorOf(node: MonoLinkedNode<T> = MonoLinkedBreaker()): MonoLinkedNodeCursor<T> = Container.Mutable.of(node)

/** Create an empty cursor. */
fun <T> emptyCursor(): MonoLinkedNodeCursor<T> = Container.Mutable.empty()

/**
 * Function returns condition, `false` to indicate that iteration should stop.
 * Usually defaults to [isNotBreaker].
 *
 * May use [fyi.ioclub.commons.datamodel.operator.lazy.and], [fyi.ioclub.commons.datamodel.operator.lazy.or] and [fyi.ioclub.commons.datamodel.operator.lazy.not] to compose multiple conditions.
 *
 * @see fyi.ioclub.commons.datamodel.operator.lazy.and
 * @see fyi.ioclub.commons.datamodel.operator.lazy.or
 * @see fyi.ioclub.commons.datamodel.operator.lazy.not
 */
typealias MonoLinkedNodeHasNext<T> = (MonoLinkedNode<T>).() -> Boolean

fun <T> MonoLinkedNode<T>.toIterable(hasNext: MonoLinkedNodeHasNext<T> = MonoLinkedNode<*>::isNotBreaker) =
    object : Iterable<T> {
        override operator fun iterator(): Iterator<T> = let(::cursorOf).followIteration(hasNext)
    }

fun <T> MonoLinkedNodeCursor<T>.followIteration(hasNext: MonoLinkedNodeHasNext<T> = MonoLinkedNode<*>::isNotBreaker) =
    object : Iterator<T> {
        private var curr by this@followIteration

        override fun next(): T {
            if (!hasNext()) throw NoSuchElementException()
            val node = curr
            val item = node.item
            this.curr = node.next
            return item
        }

        override fun hasNext() = curr.hasNext()
    }

interface AdderNext<T> {

    /** @return next node added. */
    fun addNext(container: Container<T>): MonoLinkedNode.LinkMutable<T>
}

fun <T> MonoLinkedNodeCursor<T>.followAddingNext() = object : AdderNext<T> {
    private var curr by this@followAddingNext

    override fun addNext(container: Container<T>) = container.monoLinkTo(MonoLinkedBreaker()).let { next ->
        curr.linkNext(next).also {
            curr = it
        }
    }
}

interface AdderPrevious<T> {

    /** @return previous node added. */
    fun addPrevious(container: Container<T>): MonoLinkedNode.LinkMutable<T>
}

fun <T> MonoLinkedNodeCursor<T>.followAddingPrevious() = object : AdderPrevious<T> {
    private var curr by this@followAddingPrevious

    override fun addPrevious(container: Container<T>) = container.monoLinkTo(curr).also {
        curr = it
    }
}

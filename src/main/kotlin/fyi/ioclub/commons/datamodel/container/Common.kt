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

internal const val MSG_EMPTY = "Container is empty"

internal interface ContainerAsSet<out T> : Container<T>, Set<T>

internal interface MutableContainerAsMutableSet<T> : ContainerAsSet<T>, Container.Mutable<T>, MutableSet<T>

internal class IdleAbleContainer<T>() : MutableContainerAsMutableSet<T> {

    constructor(item: T) : this() {
        _item = item
    }

    override var item: T
        get() = _item.let {
            if (isEmpty()) throw NoSuchElementException(MSG_EMPTY)
            else @Suppress("UNCHECKED_CAST") (it as T)
        }
        set(value) {
            _item = value
        }
    private var _item: Any? = ItemEmpty

    private object ItemEmpty

    // Query Operations
    override val size get() = if (isEmpty()) 0 else 1
    override fun isEmpty() = _item === ItemEmpty
    override fun contains(element: T) = item == element
    override fun iterator(): MutableIterator<T> = MutableContainerMutableIterator(this, this)

    // Bulk Operations
    override fun containsAll(elements: Collection<T>) = all(::contains)

    // Modification Operations

    override fun add(element: T) = when (_item) {
        ItemEmpty -> true
        element -> false
        else -> throw IllegalStateException("Container is full")
    }

    override fun remove(element: T) = (_item == element).also {
        if (it) clear()
    }

    // Bulk Modification Operations

    override fun addAll(elements: Collection<T>) = elements.any(::add)
    override fun removeAll(elements: Collection<T>) = elements.any(::remove)
    override fun retainAll(elements: Collection<T>) = isNotEmpty() && (_item !in elements).also {
        if (it) clear()
    }

    override fun clear() {
        _item = ItemEmpty
    }

    override fun toString() = if (isEmpty()) "EmptyMutableContainer()" else "MutableContainer(item=$_item)"
}

internal open class ContainerIterator<out T>(
    protected open val container: Container<T>,
    protected open val set: Set<T>,
) : Iterator<T> {
    protected var state = ItrState.INIT

    final override fun next() = run {
        if (hasNext()) throw NoSuchElementException()
        state = ItrState.NEXT_CALLED
        container.item
    }

    final override fun hasNext() = !(set.isEmpty() || state != ItrState.INIT)
}

internal class MutableContainerMutableIterator<T>(
    override val container: Container.Mutable<T>,
    override val set: MutableSet<T>,
) :
    MutableIterator<T>, ContainerIterator<T>(container, set) {

    override fun remove() {
        if (state == ItrState.NEXT_CALLED) set.clear()
        else throw IllegalStateException()
        state = ItrState.REMOVE_CALLED
    }
}

internal enum class ItrState {
    INIT, NEXT_CALLED, REMOVE_CALLED
}

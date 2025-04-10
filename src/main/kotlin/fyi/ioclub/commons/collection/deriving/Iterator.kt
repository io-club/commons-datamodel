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

package fyi.ioclub.commons.collection.deriving

open class DerivingIterator<T, R>(
    protected open val mutableBase: MutableIterator<T>,
    protected open val mutableDerived: MutableIterator<R>,
    protected open val transform: (T) -> R,
) : MutableIterator<T>, Iterator<T> by mutableBase {

    override fun remove() = deriveAction(mutableBase::remove, mutableDerived::remove)
}

open class DerivingListIterator<T, R>(
    override val mutableBase: MutableListIterator<T>,
    override val mutableDerived: MutableListIterator<R>,
    transform: (T) -> R,
) : MutableListIterator<T>, ListIterator<T> by mutableBase,
    DerivingIterator<T, R>(mutableBase, mutableDerived, transform) {

    // Query Operations

    override fun next() = mutableBase.next()
    override fun hasNext() = mutableBase.hasNext()

    // Modification Operations

    override fun set(element: T) = deriveAction(mutableBase::set, element, mutableDerived::set, transform(element))
    override fun add(element: T) = deriveAction(mutableBase::add, element, mutableDerived::add, transform(element))
}

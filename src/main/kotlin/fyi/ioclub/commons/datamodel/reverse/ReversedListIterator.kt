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

package fyi.ioclub.commons.datamodel.reverse

fun <T> ListIterator<T>.iterateInReverse(): ListIterator<T> = let(::ListIteratorInReverse)

@JvmName("iterateInReverseMutable")
fun <T> MutableListIterator<T>.iterateInReverse(): MutableListIterator<T> = let(ListIteratorInReverse<*>::Mutable)

/** For iteration in reverse. */
private open class ListIteratorInReverse<out T>(protected open val base: ListIterator<T>) : ListIterator<T> {

    final override fun next() = base.previous()
    final override fun hasNext() = base.hasPrevious()
    final override fun hasPrevious() = base.hasNext()
    final override fun previous() = base.next()
    final override fun nextIndex() = base.previousIndex()
    final override fun previousIndex() = base.nextIndex()

    class Mutable<T>(override val base: MutableListIterator<T>) : MutableListIterator<T>,
        ListIteratorInReverse<T>(base) {

        override fun remove() = base.remove()
        override fun set(element: T) = base.set(element)
        override fun add(element: T) = base.add(element)
    }
}

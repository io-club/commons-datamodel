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

package fyi.ioclub.commons.collection.flatting

open class FlattenedIterable<out E>(protected open val nestedBases: Iterable<Iterable<E>>) : Iterable<E> {

    constructor(vararg nestedBases: Iterable<E>) : this(nestedBases.asList())

    override fun iterator(): Iterator<E> = FlattenIterator(
        object : Iterator<Iterator<E>> {

            private val itr = nestedBases.iterator()

            override fun next() = itr.next().iterator()
            override fun hasNext() = itr.hasNext()
        }
    )
}

open class FlattenedCollection<out E>(override val nestedBases: Collection<Collection<E>>) : Collection<E>,
    FlattenedIterable<E>(nestedBases) {

    constructor(vararg nestedBases: Collection<E>) : this(nestedBases.asList())

    // Query Operations

    override val size = nestedBases.sumOf { it.size }
    override fun isEmpty() = nestedBases.all { it.isEmpty() }
    override fun contains(element: @UnsafeVariance E) = nestedBases.any { element in it }

    // Bulk Operations

    override fun containsAll(elements: Collection<@UnsafeVariance E>) = elements.all { contains(it) }
}

open class FlattenedList<out E>(override val nestedBases: List<List<E>>) : List<E>, FlattenedCollection<E>(nestedBases) {

    constructor(vararg nestedBases: List<E>) : this(nestedBases.asList())

    // Positional Access Operations

    override operator fun get(index: Int): E {
        var off = 0
        nestedBases.forEach {
            val size = it.size
            val nestedIndex = index - off
            if (nestedIndex < size) return it[nestedIndex]
            else off += size
        }
        throw IndexOutOfBoundsException("Index $index is greater than size $off")
    }

    // Search Operations

    override fun indexOf(element: @UnsafeVariance E): Int {
        var off = 0
        nestedBases.forEach {
            val i = it.indexOf(element)
            if (i != -1) return off + i
            off += it.size
        }
        return -1
    }

    override fun lastIndexOf(element: @UnsafeVariance E): Int {
        var off = size
        nestedBases.asReversed().forEach {
            off -= it.size
            val i = it.lastIndexOf(element)
            if (i != -1) return off + i
        }
        return -1
    }

    override fun listIterator() = object : ListIterator<E> {

        val nestedItr = nestedBases.listIterator()
        var itr: ListIterator<@UnsafeVariance E>? = nestedItr.run { if (hasNext()) next().listIterator() else null }

        override fun next(): E {
            val itr = itr ?: nestedItr.next().listIterator().also { itr = it }
            return if (itr.hasNext()) itr.next()
            else {
                this.itr = null
                next()
            }
        }

        override fun hasNext() = itr?.hasNext() == true

        override fun hasPrevious(): Boolean {
            TODO("Not yet implemented")
//            return itr?.hasPrevious() == true
        }

        override fun previous(): E {
            TODO("Not yet implemented")
//            val itr = itr ?: nestedItr.next().listIterator().also { itr = it }
//            return if (itr.hasNext()) itr.next()
//            else {
//                this.itr = null
//                next()
//            }
        }

        override fun nextIndex(): Int {
            TODO("Not yet implemented")
        }

        override fun previousIndex(): Int {
            TODO("Not yet implemented")
        }
    }

    override fun listIterator(index: Int): ListIterator<E> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): FlattenedList<E> {
        TODO("Not yet implemented")
    }
}

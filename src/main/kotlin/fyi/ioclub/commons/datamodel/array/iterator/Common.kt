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

package fyi.ioclub.commons.datamodel.array.iterator

internal inline fun <E, A : Any> A.iteratorTmpl(
    idx: Int,
    size: Int,
    crossinline get: (Int) -> E, crossinline set: (Int, E) -> Unit
): ArrayIterator<E> = object : ArrayIterator<E> {

    private var currIdx = idx
    private var lastRetIdx = -1

    override fun next(): E {
        val i = currIdx
        if (i >= size) throw NoSuchElementException()
        currIdx = i + 1
        return getAndUpdateLastRet(i)
    }

    override fun hasNext() = currIdx < size
    override fun hasPrevious() = currIdx > 0

    override fun previous(): E {
        val i = currIdx - 1
        if (i < 0) throw NoSuchElementException()
        currIdx = i
        return getAndUpdateLastRet(i)
    }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun getAndUpdateLastRet(i: Int): E {
        val e = get(i)
        lastRetIdx = i
        return e
    }

    override fun nextIndex() = currIdx
    override fun previousIndex() = currIdx - 1

    override fun set(element: E) {
        check(lastRetIdx != -1)
        set(lastRetIdx, element)
    }
}

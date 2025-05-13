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

package fyi.ioclub.commons.datamodel.collection.flattened

open class FlattenIterator<out E>(protected open val nestedBases: Iterator<Iterator<E>>) : Iterator<E> {

    private var itr: Iterator<@UnsafeVariance E>? = nestedBases.run { if (hasNext()) next() else null }

    override fun next(): E {
        val itr = itr ?: nestedBases.next().also { itr = it }
        return if (itr.hasNext()) itr.next()
        else {
            this.itr = null
            next()
        }
    }

    override fun hasNext() = itr?.hasNext() == true
}

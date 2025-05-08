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

package fyi.ioclub.commons.datamodel.array.slice

internal fun checkIndexBounds(capacity: Int, offset: Int, length: Int) {
    if (offset < 0) throw ArrayIndexOutOfBoundsException(offset)
    (offset + length).let { if (it > capacity) throw ArrayIndexOutOfBoundsException(it) }
}

internal inline fun <A> slice(arr: A, off: Int, len: Int, sizeGetter: A.() -> Int, copier: A.(Int, Int) -> A) =
    if (off > 0 || len < arr.sizeGetter()) arr.copier(off, off + len) else arr

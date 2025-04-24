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

package fyi.ioclub.commons.datamodel.array.join

import fyi.ioclub.commons.datamodel.array.slice.simplified.get

fun testByteArrayJoining() {
    val arr1 = "array1".encodeToByteArray()
    val arr2 = "array2".encodeToByteArray()
    println(join(arr1, 0, 3, arr2).decodeToString())
    println(join(arr1, 3, arr2).decodeToString())
    println((arr1[2, 3] + arr2[2, 3]).decodeToString())
}

fun main() = testByteArrayJoining()

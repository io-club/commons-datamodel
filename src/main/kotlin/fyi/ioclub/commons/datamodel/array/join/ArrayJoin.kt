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

@SuppressWarnings("unchecked")
fun <T> join(
    clazz: Class<T>, src1: Array<T>, off1: Int, len1: Int, src2: Array<T>, off2: Int, len2: Int
) = (java.lang.reflect.Array.newInstance(clazz, len1 + len2) as Array<T>).also {
    System.arraycopy(src1, off1, it, 0, len1)
    System.arraycopy(src2, off2, it, len1, len2)
}

@JvmOverloads
fun <T> join(clazz: Class<T>, src1: Array<T>, off1: Int, len1: Int, src2: Array<T>, len2: Int = src2.size) =
    join(clazz, src1, off1, len1, src2, 0, len2)

fun <T> join(clazz: Class<T>, src1: Array<T>, len1: Int, src2: Array<T>, off2: Int, len2: Int) =
    join<T>(clazz, src1, 0, len1, src2, off2, len2)

@JvmOverloads
fun <T> join(clazz: Class<T>, src1: Array<T>, len1: Int, src2: Array<T>, len2: Int = src2.size) =
    join<T>(clazz, src1, 0, len1, src2, 0, len2)

fun <T> join(clazz: Class<T>, src1: Array<T>, src2: Array<T>, off2: Int, len2: Int) =
    join<T>(clazz, src1, 0, src1.size, src2, off2, len2)

fun <T> join(clazz: Class<T>, src1: Array<T>, src2: Array<T>, len2: Int = src2.size) =
    join<T>(clazz, src1, 0, src1.size, src2, 0, len2)
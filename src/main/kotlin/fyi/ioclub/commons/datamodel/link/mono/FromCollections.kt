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

private fun <T> defaultCollection(size: Int? = null): ArrayList<MonoLinkedNode.LinkMutable<T>> =
    size?.let(::ArrayList) ?: ArrayList()

fun <T> Iterator<Container<T>>.toMonoLinkedNodesTo(dst: MutableList<MonoLinkedNode.LinkMutable<T>>) {
    val adder = emptyCursor<T>().followAddingNext()
    forEach {
        val node = adder.addNext(it)
        dst.add(node)
    }
}

fun <T> Iterator<Container<T>>.reversedToMonoLinkedNodesTo(dst: MutableList<MonoLinkedNode.LinkMutable<T>>) {
    val adder = emptyCursor<T>().followAddingPrevious()
    forEach {
        val node = adder.addPrevious(it)
        dst.add(node)
    }
}

fun <T> Iterator<Container<T>>.toMonoLinkedNodes(): MutableList<MonoLinkedNode.LinkMutable<T>> =
    defaultCollection<T>().also(::toMonoLinkedNodesTo)

fun <T> Iterator<Container<T>>.reversedToMonoLinkedNodes(): MutableList<MonoLinkedNode.LinkMutable<T>> =
    defaultCollection<T>().also(::reversedToMonoLinkedNodesTo)

fun <T> Iterable<Container<T>>.toMonoLinkedNodesTo(dst: MutableList<MonoLinkedNode.LinkMutable<T>>) =
    iterator().toMonoLinkedNodesTo(dst)

fun <T> Iterable<Container<T>>.reversedToMonoLinkedNodeTo(dst: MutableList<MonoLinkedNode.LinkMutable<T>>) =
    iterator().reversedToMonoLinkedNodesTo(dst)

fun <T> Iterable<Container<T>>.toMonoLinkedNodes(): MutableList<MonoLinkedNode.LinkMutable<T>> = iterator().toMonoLinkedNodes()
fun <T> Iterable<Container<T>>.reversedToMonoLinkedNodes(): MutableList<MonoLinkedNode.LinkMutable<T>> =
    iterator().reversedToMonoLinkedNodes()

fun <T> Collection<Container<T>>.toMonoLinkedNodes(): MutableList<MonoLinkedNode.LinkMutable<T>> =
    defaultCollection<T>(size).also(::toMonoLinkedNodesTo)

fun <T> Collection<Container<T>>.reversedToMonoLinkedNodes(): MutableList<MonoLinkedNode.LinkMutable<T>> =
    defaultCollection<T>(size).also(::reversedToMonoLinkedNodeTo)

fun <T> List<Container<T>>.toMonoLinkedNodes(): MutableList<MonoLinkedNode.LinkMutable<T>> =
    asReversed().reversedToMonoLinkedNodes().asReversed()

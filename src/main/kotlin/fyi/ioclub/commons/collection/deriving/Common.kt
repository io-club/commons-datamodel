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

internal inline fun <RT> deriveAction(
    baseAction: () -> RT,
    derivedAction: () -> Any?,
) = run {
    val returnValue = baseAction()
    derivedAction()
    returnValue
}

internal inline fun <TP1, TR, RP1> deriveAction(
    baseAction: (TP1) -> TR,
    baseP1: TP1,
    derivedAction: (RP1) -> Any?,
    derivedP1: RP1,
) = run {
    val returnValue = baseAction(baseP1)
    derivedAction(derivedP1)
    returnValue
}

internal inline fun <TP1, TP2, TR, RP1, RP2> deriveAction(
    baseAction: (TP1, TP2) -> TR,
    baseP1: TP1,
    baseP2: TP2,
    derivedAction: (RP1, RP2) -> Any?,
    derivedP1: RP1,
    derivedP2: RP2,
) = run {
    val returnValue = baseAction(baseP1, baseP2)
    derivedAction(derivedP1, derivedP2)
    returnValue
}

internal inline fun <T, R, C : MutableCollection<in R>> deriveCollection(
    base: MutableCollection<T>,
    transform: (T) -> R,
    noinline derivedCollectionFactory: (Int) -> C,
) = derivedCollectionFactory(base.size).also { base.mapTo(it, transform) }

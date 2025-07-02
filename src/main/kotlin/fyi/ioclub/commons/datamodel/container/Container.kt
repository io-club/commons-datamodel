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

package fyi.ioclub.commons.datamodel.container

import fyi.ioclub.commons.datamodel.container.Container.Mutable
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface Container<out T> {

    val item: T

    interface Mutable<T> : Container<T> {

        override var item: T

        companion object {

            fun <T> of(item: T): Mutable<T> = object : Mutable<T>, AbstractContainerImpl() {
                override var item = item
                override fun toString() = "MutableContainer(item=$item)"
            }

            fun <T> ofProperty(property: ReadWriteProperty<Container<T>, T>): Mutable<T> =
                object : Mutable<T>, AbstractContainerImpl() {
                    override var item by property
                    override fun toString() = "MutableContainer(property=$property)"
                }

            fun <T> ofGetterSetter(getter: () -> T, setter: (T) -> Unit) =
                ofProperty(object : ReadWriteProperty<Container<T>, T> {
                    override fun getValue(thisRef: Container<T>, property: KProperty<*>) = getter()
                    override fun setValue(thisRef: Container<T>, property: KProperty<*>, value: T) = setter(value)
                })


            fun <T> ofInitializer(initializer: () -> T): Mutable<T> = LazyContainer(initializer)

            fun <T> empty(): Mutable<T> = object : LazyContainer<T>({ throw NoSuchElementException(MSG_EMPTY) }) {
                override fun toString() = if (isEmpty) NAME_EMPTY else super.toString()
            }

            internal const val NAME_EMPTY = "EmptyMutableContainer()"

            private open class LazyContainer<T>(private val initializer: () -> T) : Mutable<T> {

                final override var item: T
                    get() = _item.let {
                        if (isEmpty) initializer().also { initial ->
                            _item = initial
                        }
                        else @Suppress("UNCHECKED_CAST") (it as T)
                    }
                    set(value) {
                        _item = value
                    }

                protected val isEmpty get() = _item === EmptyMark

                private var _item: Any? = EmptyMark

                private object EmptyMark

                override fun toString() = "MutableContainer(item=$item)"
            }
        }
    }

    companion object {

        fun <T> of(item: T): Container<T> = object : Container<T>, AbstractContainerImpl() {
            override val item = item
            override fun toString() = "ImmutableContainer(item=$item)"
        }

        fun <T> ofProperty(property: ReadOnlyProperty<Container<T>, T>): Container<T> =
            object : Container<T>, AbstractContainerImpl() {
                override val item by property
                override fun toString() = "ImmutableContainer(property=$property)"
            }

        fun <T> ofGetter(getter: () -> T): Container<T> = object : Container<T>, AbstractContainerImpl() {
            override val item get() = getter()
            override fun toString() = "ImmutableContainer(getter=$getter)"
        }

        fun <T> ofInitializer(initializer: () -> T): Container<T> = object : Container<T>, AbstractContainerImpl() {
            override val item by lazy(LazyThreadSafetyMode.NONE, initializer)
            override fun toString() = "ImmutableContainer(item=$item)"
        }

        fun <T> empty() = Empty

        @Deprecated(
            message = "Use Container.ofGetter(lazy::value) instead",
            replaceWith = ReplaceWith("Container.ofGetter"),
            level = DeprecationLevel.WARNING,
        )
        fun <T> ofLazy(lazy: Lazy<T>): Container<T> = ofGetter(lazy::value)

        @Deprecated(
            message = "Use Container.ofInitializer(initializer) instead",
            replaceWith = ReplaceWith("Container.ofInitializer"),
            level = DeprecationLevel.WARNING,
        )
        fun <T> ofLazy(initializer: () -> T): Container<T> = ofInitializer(initializer)
    }

    /**
     * Works as a placeholder.
     *
     * @throws NoSuchElementException for [equals] and [hashCode].
     * Instead, use methods of [Empty.toSet] which aims for emptiable container support.
     * @see Container.toSet
     */
    object Empty : Container<Nothing> by object : Container<Nothing>, AbstractContainerImpl() {
        override val item get() = throw NoSuchElementException(MSG_EMPTY)
        override fun toString() = "EmptyImmutableContainer()"
    }

    private abstract class AbstractContainerImpl : Container<Any?> {

        final override fun equals(other: Any?) = this === other || other is Container<*> && this.item == other.item
        final override fun hashCode() = setOf(item).hashCode()
    }
}

operator fun <T> Container<T>.getValue(thisRef: Any?, property: KProperty<*>): T = item

operator fun <T> Mutable<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) = ::item.set(value)

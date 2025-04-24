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

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface Container<out T> {

    val item: T

    interface Mutable<T> : Container<T> {

        override var item: T

        companion object {

            fun <T> of(item: T) = object : Mutable<T> {
                override var item = item
                override fun toString() = "MutableContainer(item=$item)"
            }

            fun <T> ofProperty(property: ReadWriteProperty<Container<T>, T>) = object : Mutable<T> {
                override var item by property
                override fun toString() = "MutableContainer(property=$property)"
            }

            fun <T> ofGetterSetter(getter: () -> T, setter: (T) -> Unit) =
                ofProperty(object : ReadWriteProperty<Container<T>, T> {
                    override fun getValue(thisRef: Container<T>, property: KProperty<*>) = getter()
                    override fun setValue(thisRef: Container<T>, property: KProperty<*>, value: T) = setter(value)
                })

            fun <T> empty() = object : Mutable<T> {
                override var item: T
                    get() = _item.let {
                        if (isEmpty) throw NoSuchElementException(MSG_EMPTY)
                        else @Suppress("UNCHECKED_CAST") (it as T)
                    }
                    set(value) {
                        _item = value
                    }
                private val isEmpty get() = _item === EmptyMark
                private var _item: Any? = EmptyMark
                override fun toString() = if (isEmpty) "EmptyMutableContainer()" else "MutableContainer(item=$_item)"
            }

            private object EmptyMark
        }
    }

    companion object {

        fun <T> of(item: T) = object : Container<T> {
            override val item = item
            override fun toString() = "ImmutableContainer(item=$item)"
        }

        fun <T> ofProperty(property: ReadOnlyProperty<Container<T>, T>) = object : Container<T> {
            override val item by property
            override fun toString() = "ImmutableContainer(property=$property)"
        }

        fun <T> ofGetter(getter: () -> T) = ofProperty { _, _ -> getter() }

        fun <T> empty() = object : Container<T> {
            override val item get() = throw NoSuchElementException(MSG_EMPTY)
            override fun toString() = "EmptyContainer()"
        }
    }

    object Empty : Container<Nothing> {
        override val item get() = throw NoSuchElementException(MSG_EMPTY)
        override fun toString() = "EmptyImmutableContainer()"
        operator fun <T> invoke() = this as Container<T>
    }

}

operator fun <T> Container<T>.getValue(thisRef: Any?, property: KProperty<*>): T = item

operator fun <T> Container.Mutable<T>.setValue(thisRef: Any?, property: KProperty<*>, value: T) = ::item.set(value)

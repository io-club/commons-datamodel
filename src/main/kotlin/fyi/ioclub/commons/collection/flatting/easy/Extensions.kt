package fyi.ioclub.commons.collection.flatting.easy

import fyi.ioclub.commons.collection.flatting.flatten

operator fun <T> T.plus(other: Iterable<T>) = listOf(listOf(this), other).flatten()
operator fun <T> Iterable<T>.plus(other: T) = listOf(this, listOf(other)).flatten()
operator fun <T> T.plus(other: Collection<T>) = listOf(listOf(this), other).flatten()
operator fun <T> Collection<T>.plus(other: T) = listOf(this, listOf(other)).flatten()
operator fun <T> T.plus(other: List<T>) = listOf(listOf(this), other).flatten()
operator fun <T> List<T>.plus(other: T) = listOf(this, listOf(other)).flatten()

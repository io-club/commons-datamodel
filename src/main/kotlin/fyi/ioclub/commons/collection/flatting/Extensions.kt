package fyi.ioclub.commons.collection.flatting

fun <T> Iterable<Iterable<T>>.flatten(): Iterable<T> = let(::FlattenedIterable)
fun <T> Collection<Collection<T>>.flatten(): Collection<T> = let(::FlattenedCollection)
fun <T> List<List<T>>.flatten(): List<T> = let(::FlattenedList)

operator fun <T> Iterable<T>.plus(other: Iterable<T>) = listOf(this, other).flatten()
operator fun <T> Collection<T>.plus(other: Collection<T>) = listOf(this, other).flatten()
operator fun <T> List<T>.plus(other: List<T>) = listOf(this, other).flatten()

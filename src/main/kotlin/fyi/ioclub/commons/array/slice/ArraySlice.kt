package fyi.ioclub.commons.array.slice

sealed interface ArraySliceProtocol<A> {

    val array: A
    val offset: Int
    val length: Int

    fun sliced(): A
}

fun <A> ArraySliceProtocol<A>.toTriple(): Triple<A, Int, Int> = Triple(array, offset, length)

interface ArraySlice<T> : ArraySliceProtocol<Array<T>>

fun <T> arraySliceOf(array: Array<T>, offset: Int, length: Int): ArraySlice<T> = ArraySliceImpl(array, offset, length)
fun <T> arraySliceOf(array: Array<T>): ArraySlice<T> = arraySliceOf(array, 0, array.size)

private class ArraySliceImpl<T>(override val array: Array<T>, override val offset: Int, override val length: Int) :
    ArraySlice<T> {
    init {
        checkIndexBounds(array.size, offset, length)
    }

    override fun sliced() = slice(array, offset, length, Array<T>::size, Array<T>::copyOfRange)
}

package fyi.ioclub.commons.array.slice

internal fun checkIndexBounds(arrSize: Int, offset: Int, length: Int) {
    if (offset < 0) throw ArrayIndexOutOfBoundsException(offset)
    (offset + length).let { if (it > arrSize) throw ArrayIndexOutOfBoundsException(it) }
}

internal inline fun <A> slice(arr: A, off: Int, len: Int, sizeGetter: A.() -> Int, copier: A.(Int, Int) -> A) =
    if (off > 0 || len < arr.sizeGetter()) arr.copier(off, off + len) else arr

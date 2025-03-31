package fyi.ioclub.commons.array.join

import fyi.ioclub.commons.array.slice.CharArraySlice
import fyi.ioclub.commons.array.slice.arraySliceOf

@JvmOverloads
fun join(src1: CharArray, off1: Int, len1: Int, src2: CharArray, len2: Int = src2.size) =
    join(src1, off1, len1, src2, 0, len2)

fun join(src1: CharArray, off1: Int, len1: Int, src2: CharArray, off2: Int, len2: Int) = CharArray(len1 + len2).also {
    System.arraycopy(src1, off1, it, 0, len1)
    System.arraycopy(src2, off2, it, len1, len2)
}

@JvmOverloads
fun join(src1: CharArray, len1: Int, src2: CharArray, len2: Int = src2.size) = join(src1, len1, src2, 0, len2)
fun join(src1: CharArray, len1: Int, src2: CharArray, off2: Int, len2: Int) = join(src1, 0, len1, src2, off2, len2)

@JvmOverloads
fun join(src1: CharArray, src2: CharArray, len2: Int = src2.size) = join(src1, src2, 0, len2)
fun join(src1: CharArray, src2: CharArray, off2: Int, len2: Int) = join(src1, src1.size, src2, off2, len2)

fun join(src1: CharArraySlice, src2: CharArraySlice) =
    join(src1.array, src1.offset, src1.length, src2.array, src2.offset, src2.length)

fun join(src1: CharArraySlice, src2: CharArray) = join(src1, arraySliceOf(src2))
fun join(src1: CharArray, src2: CharArraySlice) = join(arraySliceOf(src1), src2)

operator fun CharArraySlice.plus(other: CharArraySlice) = join(this, other)
operator fun CharArraySlice.plus(other: CharArray) = join(this, other)
operator fun CharArray.plus(other: CharArraySlice) = join(this, other)
operator fun CharArray.plus(other: CharArray) = join(this, other)

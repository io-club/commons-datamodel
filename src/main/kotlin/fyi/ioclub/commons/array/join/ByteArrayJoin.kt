package fyi.ioclub.commons.array.join

import fyi.ioclub.commons.array.slice.ByteArraySlice
import fyi.ioclub.commons.array.slice.arraySliceOf

@JvmOverloads
fun join(src1: ByteArray, off1: Int, len1: Int, src2: ByteArray, len2: Int = src2.size) =
    join(src1, off1, len1, src2, 0, len2)

fun join(src1: ByteArray, off1: Int, len1: Int, src2: ByteArray, off2: Int, len2: Int) = ByteArray(len1 + len2).also {
    System.arraycopy(src1, off1, it, 0, len1)
    System.arraycopy(src2, off2, it, len1, len2)
}

@JvmOverloads
fun join(src1: ByteArray, len1: Int, src2: ByteArray, len2: Int = src2.size) = join(src1, len1, src2, 0, len2)
fun join(src1: ByteArray, len1: Int, src2: ByteArray, off2: Int, len2: Int) = join(src1, 0, len1, src2, off2, len2)

@JvmOverloads
fun join(src1: ByteArray, src2: ByteArray, len2: Int = src2.size) = join(src1, src2, 0, len2)
fun join(src1: ByteArray, src2: ByteArray, off2: Int, len2: Int) = join(src1, src1.size, src2, off2, len2)

fun join(src1: ByteArraySlice, src2: ByteArraySlice) =
    join(src1.array, src1.offset, src1.length, src2.array, src2.offset, src2.length)

fun join(src1: ByteArraySlice, src2: ByteArray) = join(src1, arraySliceOf(src2))
fun join(src1: ByteArray, src2: ByteArraySlice) = join(arraySliceOf(src1), src2)

operator fun ByteArraySlice.plus(other: ByteArraySlice) = join(this, other)
operator fun ByteArraySlice.plus(other: ByteArray) = join(this, other)
operator fun ByteArray.plus(other: ByteArraySlice) = join(this, other)
operator fun ByteArray.plus(other: ByteArray) = join(this, other)

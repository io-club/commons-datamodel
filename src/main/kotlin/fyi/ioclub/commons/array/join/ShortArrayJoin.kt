package fyi.ioclub.commons.array.join

import fyi.ioclub.commons.array.slice.ShortArraySlice
import fyi.ioclub.commons.array.slice.arraySliceOf

@JvmOverloads
fun join(src1: ShortArray, off1: Int, len1: Int, src2: ShortArray, len2: Int = src2.size) =
    join(src1, off1, len1, src2, 0, len2)

fun join(src1: ShortArray, off1: Int, len1: Int, src2: ShortArray, off2: Int, len2: Int) = ShortArray(len1 + len2).also {
    System.arraycopy(src1, off1, it, 0, len1)
    System.arraycopy(src2, off2, it, len1, len2)
}

@JvmOverloads
fun join(src1: ShortArray, len1: Int, src2: ShortArray, len2: Int = src2.size) = join(src1, len1, src2, 0, len2)
fun join(src1: ShortArray, len1: Int, src2: ShortArray, off2: Int, len2: Int) = join(src1, 0, len1, src2, off2, len2)

@JvmOverloads
fun join(src1: ShortArray, src2: ShortArray, len2: Int = src2.size) = join(src1, src2, 0, len2)
fun join(src1: ShortArray, src2: ShortArray, off2: Int, len2: Int) = join(src1, src1.size, src2, off2, len2)

fun join(src1: ShortArraySlice, src2: ShortArraySlice) =
    join(src1.array, src1.offset, src1.length, src2.array, src2.offset, src2.length)

fun join(src1: ShortArraySlice, src2: ShortArray) = join(src1, arraySliceOf(src2))
fun join(src1: ShortArray, src2: ShortArraySlice) = join(arraySliceOf(src1), src2)

operator fun ShortArraySlice.plus(other: ShortArraySlice) = join(this, other)
operator fun ShortArraySlice.plus(other: ShortArray) = join(this, other)
operator fun ShortArray.plus(other: ShortArraySlice) = join(this, other)
operator fun ShortArray.plus(other: ShortArray) = join(this, other)

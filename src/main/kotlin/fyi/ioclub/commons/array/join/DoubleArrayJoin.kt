package fyi.ioclub.commons.array.join

import fyi.ioclub.commons.array.slice.DoubleArraySlice
import fyi.ioclub.commons.array.slice.arraySliceOf

@JvmOverloads
fun join(src1: DoubleArray, off1: Int, len1: Int, src2: DoubleArray, len2: Int = src2.size) =
    join(src1, off1, len1, src2, 0, len2)

fun join(src1: DoubleArray, off1: Int, len1: Int, src2: DoubleArray, off2: Int, len2: Int) = DoubleArray(len1 + len2).also {
    System.arraycopy(src1, off1, it, 0, len1)
    System.arraycopy(src2, off2, it, len1, len2)
}

@JvmOverloads
fun join(src1: DoubleArray, len1: Int, src2: DoubleArray, len2: Int = src2.size) = join(src1, len1, src2, 0, len2)
fun join(src1: DoubleArray, len1: Int, src2: DoubleArray, off2: Int, len2: Int) = join(src1, 0, len1, src2, off2, len2)

@JvmOverloads
fun join(src1: DoubleArray, src2: DoubleArray, len2: Int = src2.size) = join(src1, src2, 0, len2)
fun join(src1: DoubleArray, src2: DoubleArray, off2: Int, len2: Int) = join(src1, src1.size, src2, off2, len2)

fun join(src1: DoubleArraySlice, src2: DoubleArraySlice) =
    join(src1.array, src1.offset, src1.length, src2.array, src2.offset, src2.length)

fun join(src1: DoubleArraySlice, src2: DoubleArray) = join(src1, arraySliceOf(src2))
fun join(src1: DoubleArray, src2: DoubleArraySlice) = join(arraySliceOf(src1), src2)

operator fun DoubleArraySlice.plus(other: DoubleArraySlice) = join(this, other)
operator fun DoubleArraySlice.plus(other: DoubleArray) = join(this, other)
operator fun DoubleArray.plus(other: DoubleArraySlice) = join(this, other)
operator fun DoubleArray.plus(other: DoubleArray) = join(this, other)

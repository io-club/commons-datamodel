package fyi.ioclub.commons.array.slice.simplified

import fyi.ioclub.commons.array.slice.arraySliceOf

operator fun <T> Array<T>.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

operator fun BooleanArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ByteArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun CharArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun DoubleArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun FloatArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun IntArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun LongArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

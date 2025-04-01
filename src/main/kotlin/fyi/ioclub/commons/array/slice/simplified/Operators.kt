package fyi.ioclub.commons.array.slice.simplified

import fyi.ioclub.commons.array.slice.*

operator fun <T> Array<T>.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

operator fun BooleanArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ByteArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun CharArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun DoubleArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun FloatArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun IntArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun LongArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ShortArray.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

operator fun <T> ArraySlice<T>.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

operator fun BooleanArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ByteArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun CharArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun DoubleArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun FloatArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun IntArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun LongArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)
operator fun ShortArraySlice.get(offset: Int, length: Int) = arraySliceOf(this, offset, length)

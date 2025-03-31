package fyi.ioclub.commons.array.slice

import java.nio.ByteBuffer

fun ByteBuffer.getArraySlice(length: Int = remaining()) = run {
    val pos = position()
    val slice = arraySliceOf(array(), arrayOffset() + pos, length)
    position(pos + length)
    slice
}

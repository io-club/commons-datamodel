/*
 * Copyright 2025 IO Club
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fyi.ioclub.commons.datamodel.array.slice

import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.BufferUnderflowException
import java.nio.ByteBuffer
import java.nio.charset.Charset

fun String(bytes: ByteArraySlice, charset: Charset) = bytes.run { String(array, offset, length, charset) }
fun String(bytes: ByteArraySlice) = bytes.run { String(array, offset, length) }
fun String(chars: CharArraySlice) = chars.run { String(array, offset, length) }
fun String(codePoints: IntArraySlice) = codePoints.run { String(array, offset, length) }

/** @see ByteBuffer.wrap */
fun wrapByteBuffer(arraySlice: ByteArraySlice): ByteBuffer = arraySlice.run { ByteBuffer.wrap(array, offset, length) }

/**
 * @throws  BufferUnderflowException
 *          If there are fewer than {@code length} bytes
 *          remaining in this buffer
 *
 * @throws  IndexOutOfBoundsException
 *          If the preconditions on the {@code offset} and {@code length}
 *          parameters do not hold
 *
 * @see ByteBuffer.get
 */
fun ByteBuffer.get(destination: ByteArraySlice): ByteBuffer = destination.run { this@get.get(array, offset, length) }

/**
 * If [ByteBuffer.hasArray] returns `true`
 * then return a byte array slice of [ByteBuffer.array].
 *
 * @throws java.nio.BufferUnderflowException
 * if there are fewer than length bytes remaining in this buffer
 */
fun ByteBuffer.getArraySlice(length: Int = remaining()) = if (hasArray()) {
    val pos = position()
    if (length > limit() - pos) throw BufferUnderflowException()
    val slice = array().asSlice(arrayOffset() + pos, length)
    position(pos + length)
    slice
} else {
    val slice = ByteArray(length).asSlice()
    slice.also(::get)
}

fun ByteArrayInputStream(arraySlice: ByteArraySlice) = arraySlice.run { ByteArrayInputStream(array, offset, length) }

/**
 * @throws java.io.IOException
 * If the first byte cannot be read for any reason
 * other than end of file, or if the input stream has been closed,
 * or if some other I/O error occurs.
 *
 * @see InputStream.read
 */
fun InputStream.read(arraySlice: ByteArraySlice) = arraySlice.run { read(array, offset, length) }

/**
 * @throws java.io.IOException
 * If the first byte cannot be read for any reason
 * other than end of file, or if the input stream has been closed,
 * or if some other I/O error occurs.
 */
fun InputStream.readArraySlice(length: Int = available()): ByteArraySlice {
    val slice = ByteArray(length).asSlice()
    return slice.also(::read)
}

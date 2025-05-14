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

import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import kotlin.test.assertEquals

class ExtensionsTest {

    @Test
    fun testStringFactory() {
        val s = "012"
        val b = s.toByteArray()
        assertEquals(String(b, 0, 2, Charsets.UTF_8), String(b.asSliceTo(2), Charsets.UTF_8))
        assertEquals(String(b, 0, 2), String(b.asSliceTo(2)))

        val c = s.toCharArray()
        assertEquals(String(c, 0, 2), String(c.asSliceTo(2)))

        val i = s.codePoints().toArray()
        assertEquals(String(i, 0, 2), String(i.asSliceTo(2)))
    }

    @Test
    fun testForBuffer() {
        val s3 = byteArrayOf(0, 1, 2).asSlice()

        val bufR = wrapByteBuffer(s3)
        bufR.mark()
        val s2 = ByteArray(2).asSlice()
        bufR.get(s2)
        bufR.reset()
        val s2a = bufR.getArraySlice(2)
        assert(s2 contentEquals s2a)
        assertEquals(bufR.array(), s2a.array)

        val bufW = ByteBuffer.allocate(32)
        bufW.put(s3)
        bufW.flip()
        assert(s3 contentEquals bufW.getArraySlice(bufW.remaining()))
    }

    @Test
    fun testForStream() {
        val s3 = byteArrayOf(0, 1, 2).asSlice()
        val output = ByteArrayOutputStream()
        output.write(s3)
        assert(s3 contentEquals output.toByteArraySlice())

        val input = ByteArrayInputStream(s3)
        input.mark(0)
        val s2 = ByteArray(2).asSlice()
        input.read(s2)
        input.reset()
        val s2a = input.readArraySlice(2)
        assert(s2 contentEquals s2a)
    }
}

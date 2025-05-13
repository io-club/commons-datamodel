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
    fun testBufferGet() {
        val s3 = byteArrayOf(0, 1, 2).asSlice()
        val buf = wrapByteBuffer(s3)
        buf.mark()
        val s2 = ByteArray(2).asSlice()
        buf.get(s2)
        buf.reset()
        val s2a = buf.getArraySlice(2)
        assert(s2 contentEquals s2a)
        assertEquals(buf.array(), s2a.array)
    }

    @Test
    fun testInputStreamRead() {
        val s3 = byteArrayOf(0, 1, 2).asSlice()
        val stream = ByteArrayInputStream(s3)
        stream.mark(0)
        val s2 = ByteArray(2).asSlice()
        stream.read(s2)
        stream.reset()
        val s2a = stream.readArraySlice(2)
        assert(s2 contentEquals s2a)
    }
}

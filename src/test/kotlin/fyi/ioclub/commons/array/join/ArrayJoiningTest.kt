package fyi.ioclub.commons.array.join

import fyi.ioclub.commons.array.slice.simplified.get

fun testByteArrayJoining() {
    val arr1 = "array1".encodeToByteArray()
    val arr2 = "array2".encodeToByteArray()
    println(join(arr1, 0, 3, arr2).decodeToString())
    println(join(arr1, 3, arr2).decodeToString())
    println((arr1[2, 3] + arr2[2, 3]).decodeToString())
}

fun main() = testByteArrayJoining()

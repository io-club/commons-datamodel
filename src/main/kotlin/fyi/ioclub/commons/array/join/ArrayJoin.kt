package fyi.ioclub.commons.array.join

@SuppressWarnings("unchecked")
fun <T> join(
    clazz: Class<T>, src1: Array<T>, off1: Int, len1: Int, src2: Array<T>, off2: Int, len2: Int
) = (java.lang.reflect.Array.newInstance(clazz, len1 + len2) as Array<T>).also {
    System.arraycopy(src1, off1, it, 0, len1)
    System.arraycopy(src2, off2, it, len1, len2)
}

@JvmOverloads
fun <T> join(clazz: Class<T>, src1: Array<T>, off1: Int, len1: Int, src2: Array<T>, len2: Int = src2.size) =
    join(clazz, src1, off1, len1, src2, 0, len2)

fun <T> join(clazz: Class<T>, src1: Array<T>, len1: Int, src2: Array<T>, off2: Int, len2: Int) =
    join<T>(clazz, src1, 0, len1, src2, off2, len2)

@JvmOverloads
fun <T> join(clazz: Class<T>, src1: Array<T>, len1: Int, src2: Array<T>, len2: Int = src2.size) =
    join<T>(clazz, src1, 0, len1, src2, 0, len2)

fun <T> join(clazz: Class<T>, src1: Array<T>, src2: Array<T>, off2: Int, len2: Int) =
    join<T>(clazz, src1, 0, src1.size, src2, off2, len2)

fun <T> join(clazz: Class<T>, src1: Array<T>, src2: Array<T>, len2: Int = src2.size) =
    join<T>(clazz, src1, 0, src1.size, src2, 0, len2)
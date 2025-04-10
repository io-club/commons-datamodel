package fyi.ioclub.commons.collection.flatting

open class FlattenIterator<out E>(protected open val nestedBases: Iterator<Iterator<E>>) : Iterator<E> {

    private var itr: Iterator<@UnsafeVariance E>? = nestedBases.run { if (hasNext()) next() else null }

    override fun next(): E {
        val itr = itr ?: nestedBases.next().also { itr = it }
        return if (itr.hasNext()) itr.next()
        else {
            this.itr = null
            next()
        }
    }

    override fun hasNext() = itr?.hasNext() == true
}

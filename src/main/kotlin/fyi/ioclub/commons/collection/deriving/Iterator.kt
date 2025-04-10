package fyi.ioclub.commons.collection.deriving

open class DerivingIterator<T, R>(
    protected open val mutableBase: MutableIterator<T>,
    protected open val mutableDerived: MutableIterator<R>,
    protected open val transform: (T) -> R,
) : MutableIterator<T>, Iterator<T> by mutableBase {

    override fun remove() = deriveAction(mutableBase::remove, mutableDerived::remove)
}

open class DerivingListIterator<T, R>(
    override val mutableBase: MutableListIterator<T>,
    override val mutableDerived: MutableListIterator<R>,
    transform: (T) -> R,
) : MutableListIterator<T>, ListIterator<T> by mutableBase,
    DerivingIterator<T, R>(mutableBase, mutableDerived, transform) {

    // Query Operations

    override fun next() = mutableBase.next()
    override fun hasNext() = mutableBase.hasNext()

    // Modification Operations

    override fun set(element: T) = deriveAction(mutableBase::set, element, mutableDerived::set, transform(element))
    override fun add(element: T) = deriveAction(mutableBase::add, element, mutableDerived::add, transform(element))
}

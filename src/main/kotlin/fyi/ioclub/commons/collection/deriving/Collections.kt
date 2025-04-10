package fyi.ioclub.commons.collection.deriving

open class DerivingIterable<T, R>(
    protected open val mutableBase: MutableIterable<T>,
    protected open val mutableDerived: MutableIterable<R>,
    protected open val transform: (T) -> R,
) : MutableIterable<T>, Iterable<T> by mutableBase {

    open val base: Iterable<T> get() = mutableBase
    open val derived: Iterable<R> get() = mutableDerived

    // Query Operations

    override fun iterator(): MutableIterator<T> =
        DerivingIterator(mutableBase.iterator(), mutableDerived.iterator(), transform)
}

open class DerivingCollection<T, R>(
    override val mutableBase: MutableCollection<T>,
    override val mutableDerived: MutableCollection<R>,
    transform: (T) -> R,
) : MutableCollection<T>, Collection<T> by mutableBase, DerivingIterable<T, R>(mutableBase, mutableDerived, transform) {

    override val base: Collection<T> get() = mutableBase
    override val derived: Collection<R> get() = mutableDerived

    // Query Operations

    override fun iterator() = super.iterator()

    // Modification Operations

    override fun add(element: T) = deriveAction(mutableBase::add, element, mutableDerived::add, transform(element))

    override fun remove(element: T) =
        deriveAction(mutableBase::remove, element, mutableDerived::remove, transform(element))

    // Bulk Modification Operations

    override fun addAll(elements: Collection<T>) =
        deriveAction(mutableBase::addAll, elements, mutableDerived::addAll, elements.map(transform))

    override fun removeAll(elements: Collection<T>) =
        deriveAction(mutableBase::removeAll, elements, mutableDerived::removeAll, elements.map(transform))

    override fun retainAll(elements: Collection<T>) =
        deriveAction(mutableBase::retainAll, elements, mutableDerived::retainAll, elements.map(transform))

    override fun clear() = deriveAction(mutableBase::clear, mutableDerived::clear)
}

open class DerivingList<T, R>(
    override val mutableBase: MutableList<T>,
    override val mutableDerived: MutableList<R>,
    transform: (T) -> R,
) : MutableList<T>, DerivingCollection<T, R>(mutableBase, mutableDerived, transform) {

    constructor(mutableBase: MutableList<T>, transform: (T) -> R) : this(
        mutableBase,
        deriveCollection(mutableBase, transform, ::ArrayList),
        transform,
    )

    constructor(transform: (T) -> R) : this(ArrayList(), ArrayList(), transform)

    override val base: List<T> get() = mutableBase
    override val derived: List<R> get() = mutableDerived

    // Bulk Modification Operations

    override fun addAll(index: Int, elements: Collection<T>) =
        deriveAction(mutableBase::addAll, index, elements, mutableDerived::addAll, index, elements.map(transform))

    // Positional Access Operations

    override operator fun get(index: Int) = mutableBase[index]

    override operator fun set(index: Int, element: T) =
        deriveAction(mutableBase::set, index, element, mutableDerived::set, index, transform(element))

    override fun add(index: Int, element: T) =
        deriveAction(mutableBase::add, index, element, mutableDerived::add, index, transform(element))

    override fun removeAt(index: Int) = deriveAction(mutableBase::removeAt, index, mutableDerived::removeAt, index)

    // Search Operations

    override fun indexOf(element: T) = mutableBase.indexOf(element)
    override fun lastIndexOf(element: T) = mutableBase.lastIndexOf(element)

    // List Iterators

    override fun listIterator(): MutableListIterator<T> =
        DerivingListIterator(mutableBase.listIterator(), mutableDerived.listIterator(), transform)

    override fun listIterator(index: Int): MutableListIterator<T> =
        DerivingListIterator(mutableBase.listIterator(index), mutableDerived.listIterator(index), transform)

    // View

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> =
        DerivingList(mutableBase.subList(fromIndex, toIndex), mutableDerived.subList(fromIndex, toIndex), transform)
}

open class DerivingSet<T, R>(
    override val mutableBase: MutableSet<T>,
    override val mutableDerived: MutableSet<R>, transform: (T) -> R
) : MutableSet<T>, DerivingCollection<T, R>(mutableBase, mutableDerived, transform) {

    constructor(mutableBase: MutableSet<T>, transform: (T) -> R) : this(
        mutableBase,
        deriveCollection(mutableBase, transform, ::LinkedHashSet),
        transform,
    )

    constructor(transform: (T) -> R) : this(LinkedHashSet(), LinkedHashSet(), transform)

    override val base: Set<T> get() = mutableBase
    override val derived: Set<R> get() = mutableDerived
}

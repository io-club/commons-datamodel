package fyi.ioclub.commons.collection.deriving

internal inline fun <RT> deriveAction(
    baseAction: () -> RT,
    derivedAction: () -> Any?,
) = run {
    val returnValue = baseAction()
    derivedAction()
    returnValue
}

internal inline fun <TP1, TR, RP1> deriveAction(
    baseAction: (TP1) -> TR,
    baseP1: TP1,
    derivedAction: (RP1) -> Any?,
    derivedP1: RP1,
) = run {
    val returnValue = baseAction(baseP1)
    derivedAction(derivedP1)
    returnValue
}

internal inline fun <TP1, TP2, TR, RP1, RP2> deriveAction(
    baseAction: (TP1, TP2) -> TR,
    baseP1: TP1,
    baseP2: TP2,
    derivedAction: (RP1, RP2) -> Any?,
    derivedP1: RP1,
    derivedP2: RP2,
) = run {
    val returnValue = baseAction(baseP1, baseP2)
    derivedAction(derivedP1, derivedP2)
    returnValue
}

internal inline fun <T, R, C : MutableCollection<in R>> deriveCollection(
    base: MutableCollection<T>,
    transform: (T) -> R,
    noinline derivedCollectionFactory: (Int) -> C,
) = derivedCollectionFactory(base.size).also { base.mapTo(it, transform) }

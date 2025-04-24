# commons-datamodel

IO-Club Commons Datamodel

Usage:

Gradle - publishToMavenLocal

## `.array`

## `.collection`

- In `fyi.ioclub.commons.datamodel.collection.deriving`, we sync modifications
  from `MutableCollection<T>` to `MutableCollection<R>` with `transform: (T) -> R`.

- In `fyi.ioclub.commons.datamodel.collection.flatting`, we make `Collection<Collection<E>>`
  interpreted as a flattened `Collection<E>`.

- In `fyi.ioclub.commons.datamodel.collection.qmparted`, we separate a `MutableCollection<E>`
  to `query: Collection<Q>` and
  `modification: fyi.ioclub.commons.datamodel.collection.qmparted.CollectionMod<Q>`.
  Thus `query` is adapted to type `MutableCollection<Q>` with the modification operations
  interpreted as just events on the collection.
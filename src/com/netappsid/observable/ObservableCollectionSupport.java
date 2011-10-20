package com.netappsid.observable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public interface ObservableCollectionSupport<E>
{

	public void addCollectionChangeListener(CollectionChangeListener listener);

	public void removeCollectionChangeListener(CollectionChangeListener listener);

	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();

	public CollectionChangeEvent newCollectionChangeEvent(CollectionDifference<E> difference);

	public CollectionChangeEvent newCollectionChangeEvent(CollectionDifference<E> difference, int index);

	public void fireCollectionChangeEvent(ImmutableSet<E> oldSet, ImmutableSet<E> newSet);

	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList);

	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList, int index);

	public void fireCollectionChangeEvent(CollectionChangeEvent<E> event);

}
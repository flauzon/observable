package com.netappsid.observable;

import com.google.common.collect.ImmutableList;

public interface ObservableCollectionSupport<E, T>
{

	public void addCollectionChangeListener(CollectionChangeListener listener);

	public void removeCollectionChangeListener(CollectionChangeListener listener);

	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();

	public void fireCollectionChangeEvent();

	public void fireCollectionChangeEvent(Object index);

	public void createSnapshot();

	void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent);

	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference);

	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference, Object index);

}
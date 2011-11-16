package com.netappsid.observable;

import com.google.common.collect.ImmutableList;

public interface ObservableCollectionSupport<E, T>
{

	public void addCollectionChangeListener(CollectionChangeListener listener);

	public void removeCollectionChangeListener(CollectionChangeListener listener);

	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();

	public void fireCollectionChangeEvent(T oldCollection, T newCollection);

	public void fireCollectionChangeEvent(T oldCollection, T newCollection, Object index);

	public T copySource();

	void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent);

}
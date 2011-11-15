package com.netappsid.observable;

import com.google.common.collect.ImmutableList;

public interface ObservableCollectionSupport<E>
{

	public void addCollectionChangeListener(CollectionChangeListener listener);

	public void removeCollectionChangeListener(CollectionChangeListener listener);

	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();

	public <T> void fireCollectionChangeEvent(T oldCollection, T newCollection);

	public <T> void fireCollectionChangeEvent(T oldCollection, T newCollection, Object index);

	public <T> T copySource();

	void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent);

}
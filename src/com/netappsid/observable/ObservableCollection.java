package com.netappsid.observable;

import java.io.Serializable;

import com.google.common.collect.ImmutableList;

public interface ObservableCollection<E> extends Iterable<E>, Serializable
{
	void addCollectionChangeListener(CollectionChangeListener<E> listener);

	void removeCollectionChangeListener(CollectionChangeListener<E> listener);

	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();

	void executeBatchAction(BatchAction action);

	<T> CollectionChangeEvent<E> createCollectionChangeEvent(T oldCollection, T newCollection, Object index);

	<T> T copyInternal();
}

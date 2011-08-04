package com.netappsid.observable;

import java.util.Collection;

import com.google.common.collect.ImmutableList;

public interface ObservableCollection<E> extends Collection<E>
{
	void addCollectionChangeListener(CollectionChangeListener<E> listener);

	void removeCollectionChangeListener(CollectionChangeListener<E> listener);
	
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();

	void executeBatchAction(BatchAction action);
}

package com.netappsid.observable;

import java.util.Collection;

public interface ObservableCollection<E> extends Collection<E>
{
	void addCollectionChangeListener(CollectionChangeListener<E> listener);

	void removeCollectionChangeListener(CollectionChangeListener<E> listener);
	
	void executeBatchAction(BatchAction action);
}

/**
 * 
 */
package com.netappsid.observable;

import com.google.common.collect.ImmutableList;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public interface ObservableCollectionSupport<E>
{

	void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent);

	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference);

	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference, Object index);

	public void addCollectionChangeListener(CollectionChangeListener listener);

	public void removeCollectionChangeListener(CollectionChangeListener listener);

	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners();
}

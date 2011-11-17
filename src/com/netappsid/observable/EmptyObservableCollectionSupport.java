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
public class EmptyObservableCollectionSupport<E> implements ObservableCollectionSupport<E>
{

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(com.netappsid.observable.CollectionChangeEvent)
	 */
	@Override
	public void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference, java.lang.Object)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference, Object index)
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#addCollectionChangeListener(com.netappsid.observable.CollectionChangeListener)
	 */
	@Override
	public void addCollectionChangeListener(CollectionChangeListener listener)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#removeCollectionChangeListener(com.netappsid.observable.CollectionChangeListener)
	 */
	@Override
	public void removeCollectionChangeListener(CollectionChangeListener listener)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#getCollectionChangeListeners()
	 */
	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return null;
	}

}

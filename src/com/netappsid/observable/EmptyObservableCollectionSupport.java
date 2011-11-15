package com.netappsid.observable;

import com.google.common.collect.ImmutableList;

public class EmptyObservableCollectionSupport<E> implements ObservableCollectionSupport<E>
{

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(java.lang.Object, java.lang.Object)
	 */
	@Override
	public <T> void fireCollectionChangeEvent(T oldCollection, T newCollection)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public <T> void fireCollectionChangeEvent(T oldCollection, T newCollection, Object index)
	{}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#copySource()
	 */
	@Override
	public <T> T copySource()
	{
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(com.netappsid.observable.CollectionChangeEvent)
	 */
	@Override
	public void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent)
	{}

}

package com.netappsid.observable;

import com.netappsid.observable.internal.DefaultObservableCollectionSupport;
import com.netappsid.observable.internal.InternalObservableCollection;

public abstract class AbstractObservableCollectionSupport<E, T> extends DefaultObservableCollectionSupport<E, InternalObservableCollection<E, T>> implements
		InternalObservableCollectionSupport<E>
{
	private T snapshot;

	public AbstractObservableCollectionSupport(InternalObservableCollection<E, T> source)
	{
		super(source);
	}

	@Override
	public void fireCollectionChangeEvent()
	{
		fireCollectionChangeEvent(-1);
	}

	@Override
	public void fireCollectionChangeEvent(Object index)
	{
		CollectionChangeEvent<E> collectionChangeEvent = createCollectionChangeEvent(index);
		fireCollectionChangeEvent(collectionChangeEvent);

	}

	protected abstract CollectionChangeEvent<E> createCollectionChangeEvent(Object index);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#copySource()
	 */
	@Override
	public void createSnapshot()
	{
		snapshot = takeSnapshot();
	}

	public T takeSnapshot()
	{
		return getSource().copyInternal();
	}

	/**
	 * @return the snapshot
	 */
	public T getSnapshot()
	{
		return snapshot;
	}

}

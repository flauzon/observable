package com.netappsid.observable;

import static com.google.common.collect.Lists.*;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.internal.InternalObservableCollection;

public abstract class AbstractObservableCollectionSupport<E, T> implements ObservableCollectionSupport<E, T>
{
	private final InternalObservableCollection<E, T> source;
	private final List<CollectionChangeListener<E>> listeners = newArrayList();
	private T snapshot;

	public AbstractObservableCollectionSupport(InternalObservableCollection<E, T> source)
	{
		this.source = source;
	}

	@Override
	public void addCollectionChangeListener(CollectionChangeListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public void removeCollectionChangeListener(CollectionChangeListener listener)
	{
		listeners.remove(listener);
	}

	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return ImmutableList.copyOf(listeners);
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

	/**
	 * @param event
	 */
	@Override
	public void fireCollectionChangeEvent(CollectionChangeEvent<E> collectionChangeEvent)
	{
		if (collectionChangeEvent.getDifference().hasDifference())
		{
			for (CollectionChangeListener listener : listeners)
			{
				listener.onCollectionChange(collectionChangeEvent);
			}
		}
	}

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
		return source.copyInternal();
	}

	/**
	 * @return the snapshot
	 */
	public T getSnapshot()
	{
		return snapshot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference)
	{
		return new CollectionChangeEvent<E>(source, difference);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference, java.lang.Object)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference, Object index)
	{
		return new CollectionChangeEvent<E>(source, difference, index);
	}

}

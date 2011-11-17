/**
 * 
 */
package com.netappsid.observable;

import static com.google.common.collect.Lists.*;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class DefaultObservableCollectionSupport<E> implements ObservableCollectionSupport<E>
{
	private final List<CollectionChangeListener<E>> listeners = newArrayList();
	private final ObservableCollection<E> source;

	/**
	 * 
	 */
	public DefaultObservableCollectionSupport(ObservableCollection<E> source)
	{
		this.source = source;
	}

	/**
	 * @return the source
	 */
	public ObservableCollection<E> getSource()
	{
		return source;
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

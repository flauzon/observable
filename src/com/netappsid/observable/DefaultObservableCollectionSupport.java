package com.netappsid.observable;

import static com.google.common.collect.Lists.*;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class DefaultObservableCollectionSupport<E> implements ObservableCollectionSupport<E>
{
	private final ObservableCollection<E> source;
	private final List<CollectionChangeListener<E>> listeners = newArrayList();

	public DefaultObservableCollectionSupport(ObservableCollection<E> source)
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
	public <T> void fireCollectionChangeEvent(T oldCollection, T newCollection)
	{
		fireCollectionChangeEvent(oldCollection, newCollection, -1);
	}

	@Override
	public <T> void fireCollectionChangeEvent(T oldCollection, T newCollection, Object index)
	{
		CollectionChangeEvent<E> collectionChangeEvent = source.createCollectionChangeEvent(oldCollection, newCollection, index);
		fireCollectionChangeEvent(collectionChangeEvent);

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
	 * @see com.netappsid.observable.ObservableCollectionSupport#copySource()
	 */
	@Override
	public <T> T copySource()
	{
		return source.copyInternal();
	}

}

package com.netappsid.observable;

import static com.google.common.collect.Lists.*;

import java.util.List;

import com.google.common.collect.ImmutableList;

public abstract class AbstractObservableCollectionSupport<E, T> implements ObservableCollectionSupport<E, T>
{
	private final ObservableCollection<E> source;
	private final List<CollectionChangeListener<E>> listeners = newArrayList();

	public AbstractObservableCollectionSupport(ObservableCollection<E> source)
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
	public void fireCollectionChangeEvent(T oldCollection, T newCollection)
	{
		fireCollectionChangeEvent(oldCollection, newCollection, -1);
	}

	@Override
	public void fireCollectionChangeEvent(T oldCollection, T newCollection, Object index)
	{
		CollectionChangeEvent<E> collectionChangeEvent = createCollectionChangeEvent(oldCollection, newCollection, index);
		fireCollectionChangeEvent(collectionChangeEvent);

	}

	protected abstract CollectionChangeEvent<E> createCollectionChangeEvent(T oldCollection, T newCollection, Object index);

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
	public T copySource()
	{
		return source.copyInternal();
	}

	/**
	 * @return the source
	 */
	protected ObservableCollection<E> getSource()
	{
		return source;
	}

}

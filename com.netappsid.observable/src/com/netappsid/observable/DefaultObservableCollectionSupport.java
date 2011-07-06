package com.netappsid.observable;

import static com.google.common.collect.Maps.*;
import static com.google.common.collect.Sets.*;

import java.util.Map;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.netappsid.observable.internal.SetDifference;

public class DefaultObservableCollectionSupport<E> implements ObservableCollectionSupport<E>
{
	private final ObservableCollection<E> source;
	private final Map<CollectionChangeListener<E>, SwingSafeCollectionChangeListener<E>> listeners = newHashMap();

	public DefaultObservableCollectionSupport(ObservableCollection<E> source)
	{
		this.source = source;
	}

	@Override
	public void addCollectionChangeListener(CollectionChangeListener listener)
	{
		// Wrap CollectionChangeListener with a SwingSafe listener to ensure invocation on UI thread.
		SwingSafeCollectionChangeListener swingSafeCollectionChangeListener = new SwingSafeCollectionChangeListener(listener);
		listeners.put(listener, swingSafeCollectionChangeListener);
	}

	@Override
	public void removeCollectionChangeListener(CollectionChangeListener listener)
	{
		listeners.remove(listener);
	}

	@Override
	public ImmutableMap<CollectionChangeListener<E>, SwingSafeCollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return ImmutableMap.copyOf(listeners);
	}

	@Override
	public CollectionChangeEvent newCollectionChangeEvent(CollectionDifference<E> difference)
	{
		return new CollectionChangeEvent<E>(source, difference);
	}

	@Override
	public CollectionChangeEvent newCollectionChangeEvent(CollectionDifference<E> difference, int index)
	{
		return new CollectionChangeEvent<E>(source, difference, index);
	}

	@Override
	public void fireCollectionChangeEvent(ImmutableSet<E> oldSet, ImmutableSet<E> newSet)
	{
		final ImmutableSet<E> added = ImmutableSet.copyOf(difference(newSet, oldSet));
		final ImmutableSet<E> removed = ImmutableSet.copyOf(difference(oldSet, newSet));
		fireCollectionChangeEvent((CollectionChangeEvent<E>) newCollectionChangeEvent(new SetDifference(removed, added)));
	}

	@Override
	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList)
	{
		final CollectionDifference<E> difference = ListDifference.difference(oldList, newList);
		fireCollectionChangeEvent(newCollectionChangeEvent(difference));
	}

	@Override
	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList, int index)
	{
		final CollectionDifference<E> difference = ListDifference.difference(oldList, newList);
		fireCollectionChangeEvent(newCollectionChangeEvent(difference, index));
	}

	@Override
	public void fireCollectionChangeEvent(CollectionChangeEvent<E> event)
	{
		if (event.getDifference().hasDifference())
		{
			for (CollectionChangeListener listener : listeners.values())
			{
				listener.onCollectionChange(event);
			}
		}
	}
}

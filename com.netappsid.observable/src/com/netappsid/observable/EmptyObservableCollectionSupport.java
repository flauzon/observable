package com.netappsid.observable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class EmptyObservableCollectionSupport<E> implements ObservableCollectionSupport<E>
{

	@Override
	public void addCollectionChangeListener(CollectionChangeListener listener)
	{
		

	}

	@Override
	public void removeCollectionChangeListener(CollectionChangeListener listener)
	{
		

	}

	@Override
	public ImmutableMap<CollectionChangeListener<E>, SwingSafeCollectionChangeListener<E>> getCollectionChangeListeners()
	{
		
		return null;
	}

	@Override
	public CollectionChangeEvent newCollectionChangeEvent(CollectionDifference<E> difference)
	{
		
		return null;
	}

	@Override
	public CollectionChangeEvent newCollectionChangeEvent(CollectionDifference<E> difference, int index)
	{
		
		return null;
	}

	@Override
	public void fireCollectionChangeEvent(ImmutableSet<E> oldSet, ImmutableSet<E> newSet)
	{
		

	}

	@Override
	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList)
	{
		

	}

	@Override
	public void fireCollectionChangeEvent(ImmutableList<E> oldList, ImmutableList<E> newList, int index)
	{
		

	}

	@Override
	public void fireCollectionChangeEvent(CollectionChangeEvent<E> event)
	{
		

	}

}

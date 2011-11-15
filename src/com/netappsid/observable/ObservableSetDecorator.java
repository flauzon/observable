package com.netappsid.observable;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.netappsid.observable.internal.SetDifference;

class ObservableSetDecorator<E> extends AbstractObservableCollectionDecorator<E, ImmutableSet<E>> implements ObservableSet<E>
{

	ObservableSetDecorator(Set<E> source, ObservableCollectionSupport<E> support)
	{
		super(source, support);
	}

	ObservableSetDecorator(Set<E> source)
	{
		super(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#copyOf(java.util.Collection)
	 */
	@Override
	protected ImmutableSet<E> copyOf(Collection<E> internal)
	{
		return ImmutableSet.copyOf(internal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#createCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public <T> CollectionChangeEvent<E> createCollectionChangeEvent(T oldCollection, T newCollection, Object index)
	{
		ImmutableSet<E> oldSet = (ImmutableSet<E>) oldCollection;
		ImmutableSet<E> newSet = (ImmutableSet<E>) newCollection;
		final ImmutableSet<E> added = ImmutableSet.copyOf(Sets.difference(newSet, oldSet));
		final ImmutableSet<E> removed = ImmutableSet.copyOf(Sets.difference(oldSet, newSet));
		return new CollectionChangeEvent<E>(this, new SetDifference(removed, added));
	}
}

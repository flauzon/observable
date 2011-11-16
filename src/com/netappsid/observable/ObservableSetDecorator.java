package com.netappsid.observable;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.netappsid.observable.internal.SetObservableCollectionSupport;

class ObservableSetDecorator<E> extends AbstractObservableCollectionDecorator<E, ImmutableSet<E>> implements ObservableSet<E>
{

	ObservableSetDecorator(Set<E> source, ObservableCollectionSupport<E, ImmutableSet<E>> support)
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
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#newSupport()
	 */
	@Override
	protected ObservableCollectionSupport<E, ImmutableSet<E>> newSupport()
	{
		return new SetObservableCollectionSupport<E>(this);
	}

}

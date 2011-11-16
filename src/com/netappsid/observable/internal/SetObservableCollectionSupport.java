/**
 * 
 */
package com.netappsid.observable.internal;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.netappsid.observable.AbstractObservableCollectionSupport;
import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.ObservableSet;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class SetObservableCollectionSupport<E> extends AbstractObservableCollectionSupport<E, ImmutableSet<E>>
{

	/**
	 * @param source
	 */
	public SetObservableCollectionSupport(ObservableSet<E> source)
	{
		super(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionSupport#createCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	protected CollectionChangeEvent<E> createCollectionChangeEvent(ImmutableSet<E> oldSet, ImmutableSet<E> newSet, Object index)
	{
		final ImmutableSet<E> added = ImmutableSet.copyOf(Sets.difference(newSet, oldSet));
		final ImmutableSet<E> removed = ImmutableSet.copyOf(Sets.difference(oldSet, newSet));
		return new CollectionChangeEvent<E>(getSource(), new SetDifference(removed, added));
	}

}

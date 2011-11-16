/**
 * 
 */
package com.netappsid.observable.internal;

import com.google.common.collect.ImmutableList;
import com.netappsid.observable.AbstractObservableCollectionSupport;
import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.ListDifference;
import com.netappsid.observable.ObservableList;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class ListObservableCollectionSupport<E> extends AbstractObservableCollectionSupport<E, ImmutableList<E>>
{

	/**
	 * @param source
	 */
	public ListObservableCollectionSupport(ObservableList<E> source)
	{
		super(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionSupport#createCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	protected CollectionChangeEvent<E> createCollectionChangeEvent(ImmutableList<E> oldCollection, ImmutableList<E> newCollection, Object index)
	{
		ListDifference<E> difference = ListDifference.difference(oldCollection, newCollection);
		return new CollectionChangeEvent<E>(getSource(), difference, index);
	}

}

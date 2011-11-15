/**
 * 
 */
package com.netappsid.observable;

import java.util.Collection;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class ObservableCollectionDecorator<E> extends AbstractObservableCollectionDecorator<E, ImmutableCollection<E>>
{

	/**
	 * @param source
	 */
	public ObservableCollectionDecorator(Collection<E> source, ObservableCollectionSupport<E> support)
	{
		super(source, support);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#copyOf(java.util.Collection)
	 */
	@Override
	protected ImmutableCollection<E> copyOf(Collection<E> internal)
	{
		return ImmutableList.copyOf(internal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#createCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public <T> CollectionChangeEvent<E> createCollectionChangeEvent(T oldCollection, T newCollection, Object index)
	{
		// TODO
		throw new RuntimeException();
	}
}

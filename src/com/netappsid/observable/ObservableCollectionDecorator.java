/**
 * 
 */
package com.netappsid.observable;

import java.util.Collection;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class ObservableCollectionDecorator<E, T> extends AbstractObservableCollectionDecorator<E, T>
{

	/**
	 * @param source
	 */
	public ObservableCollectionDecorator(Collection<E> source, ObservableCollectionSupport<E, T> support)
	{
		super(source, support);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#copyOf(java.util.Collection)
	 */
	@Override
	protected T copyOf(Collection<E> internal)
	{
		throw new UnsupportedOperationException("ObservableCollectionDecorator is not directly observable");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionDecorator#newSupport()
	 */
	@Override
	protected ObservableCollectionSupport<E, T> newSupport()
	{
		throw new UnsupportedOperationException("ObservableCollectionDecorator is not directly observable");
	}

}

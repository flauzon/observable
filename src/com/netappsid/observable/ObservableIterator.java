package com.netappsid.observable;

import java.util.Iterator;

public class ObservableIterator<E> implements Iterator<E>
{
	private final Iterator<E> internal;
	private final ObservableCollectionSupport<E> observableSupport;
	private E next;

	public ObservableIterator(Iterator<E> sourceIterator, ObservableCollectionSupport<E> sourceSupport)
	{
		this.internal = sourceIterator;
		this.observableSupport = sourceSupport;
	}

	@Override
	public boolean hasNext()
	{
		return internal.hasNext();
	}

	@Override
	public E next()
	{
		next = internal.next();
		return next;
	}

	@Override
	public void remove()
	{
		Object oldCollection = observableSupport.copySource();
		internal.remove();
		Object newCollection = observableSupport.copySource();
		observableSupport.fireCollectionChangeEvent(oldCollection, newCollection);
	}
}

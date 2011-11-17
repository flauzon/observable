package com.netappsid.observable;

import java.util.Iterator;

public class ObservableIterator<E, T> implements Iterator<E>
{
	private final Iterator<E> internal;
	private final InternalObservableCollectionSupport<E> observableSupport;
	private E next;

	public ObservableIterator(Iterator<E> sourceIterator, InternalObservableCollectionSupport<E> sourceSupport)
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
		observableSupport.createSnapshot();
		internal.remove();
		observableSupport.fireCollectionChangeEvent();
	}
}

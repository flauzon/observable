package com.netappsid.observable;

import java.util.ListIterator;

public final class ObservableListIterator<E, T> implements ListIterator<E>
{
	private final ListIterator<E> internal;
	private final InternalObservableCollectionSupport<E> support;
	private E element;
	private int index;

	public ObservableListIterator(ListIterator<E> sourceIterator, InternalObservableCollectionSupport<E> sourceSupport)
	{
		this.internal = sourceIterator;
		this.support = sourceSupport;
		this.index = -1;
	}

	@Override
	public void add(E e)
	{
		support.createSnapshot();
		internal.add(e);
		final int eventIndex = internal.previousIndex() != -1 ? internal.previousIndex() : 0;
		support.fireCollectionChangeEvent(eventIndex);
	}

	@Override
	public boolean hasNext()
	{
		return internal.hasNext();
	}

	@Override
	public boolean hasPrevious()
	{
		return internal.hasPrevious();
	}

	@Override
	public E next()
	{
		index = nextIndex();
		element = internal.next();
		return element;
	}

	@Override
	public int nextIndex()
	{
		return internal.nextIndex();
	}

	@Override
	public E previous()
	{
		index = previousIndex();
		element = internal.previous();
		return element;
	}

	@Override
	public int previousIndex()
	{
		return internal.previousIndex();
	}

	@Override
	public void remove()
	{
		support.createSnapshot();
		internal.remove();
		support.fireCollectionChangeEvent(index);
	}

	@Override
	public void set(E e)
	{
		support.createSnapshot();
		internal.set(e);
		support.fireCollectionChangeEvent(index);
	}
}

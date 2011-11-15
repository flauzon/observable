package com.netappsid.observable;

import java.util.ListIterator;

public final class ObservableListIterator<E> implements ListIterator<E>
{
	private final ListIterator<E> internal;
	private final ObservableCollectionSupport<E> support;
	private E element;
	private int index;

	public ObservableListIterator(ListIterator<E> sourceIterator, ObservableCollectionSupport<E> sourceSupport)
	{
		this.internal = sourceIterator;
		this.support = sourceSupport;
		this.index = -1;
	}

	@Override
	public void add(E e)
	{
		Object oldSource = support.copySource();
		internal.add(e);
		Object newSource = support.copySource();
		final int eventIndex = internal.previousIndex() != -1 ? internal.previousIndex() : 0;
		support.fireCollectionChangeEvent(oldSource, newSource, eventIndex);
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
		Object oldSource = support.copySource();
		internal.remove();
		Object newSource = support.copySource();
		support.fireCollectionChangeEvent(oldSource, newSource, index);
	}

	@Override
	public void set(E e)
	{
		Object oldSource = support.copySource();
		internal.set(e);
		Object newSource = support.copySource();
		support.fireCollectionChangeEvent(oldSource, newSource, index);
	}
}

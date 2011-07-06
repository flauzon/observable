package com.netappsid.observable;

import java.util.ListIterator;

import com.google.common.collect.ImmutableList;

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

	public void add(E e)
	{
		internal.add(e);
		final int eventIndex = internal.previousIndex() != -1 ? internal.previousIndex() : 0;
		final CollectionChangeEvent event = support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.<E> of(), ImmutableList.of(e)), eventIndex);
		support.fireCollectionChangeEvent(event);
	}

	public boolean hasNext()
	{
		return internal.hasNext();
	}

	public boolean hasPrevious()
	{
		return internal.hasPrevious();
	}

	public E next()
	{
		index = nextIndex();
		element = internal.next();
		return element;
	}

	public int nextIndex()
	{
		return internal.nextIndex();
	}

	public E previous()
	{
		index = previousIndex();
		element = internal.previous();
		return element;
	}

	public int previousIndex()
	{
		return internal.previousIndex();
	}

	public void remove()
	{
		internal.remove();
		support.fireCollectionChangeEvent(support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.of(element), ImmutableList.<E> of()), index));
	}

	public void set(E e)
	{
		internal.set(e);
		support.fireCollectionChangeEvent(support.newCollectionChangeEvent(new ListDifference<E>(ImmutableList.of(element), ImmutableList.of(e)), index));
	}
}

package com.netappsid.observable;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ImmutableList;

abstract class AbstractObservableCollectionDecorator<E> implements ObservableCollection<E>, Serializable
{
	private final Collection<E> internal;
	private transient ObservableCollectionSupport<E> support;

	public AbstractObservableCollectionDecorator(Collection<E> source)
	{
		this.internal = source;
	}

	@Override
	public int size()
	{
		return internal.size();
	}

	@Override
	public boolean isEmpty()
	{
		return internal.isEmpty();
	}

	@Override
	public boolean contains(Object o)
	{
		return internal.contains(o);
	}

	@Override
	public Iterator<E> iterator()
	{
		return new ObservableIterator<E>(internal.iterator(), getSupport());
	}

	@Override
	public Object[] toArray()
	{
		return internal.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		return internal.toArray(a);
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		return internal.containsAll(c);
	}

	@Override
	public boolean equals(Object o)
	{
		return internal.equals(o);
	}

	@Override
	public int hashCode()
	{
		return internal.hashCode();
	}

	@Override
	public void executeBatchAction(BatchAction action)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		action.execute(internal);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal));
	}

	protected ObservableCollectionSupport<E> getSupport()
	{
		if (support == null)
		{
			this.support = new DefaultObservableCollectionSupport<E>(this);
		}

		return support;
	}
}

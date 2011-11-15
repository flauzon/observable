package com.netappsid.observable;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

abstract class AbstractObservableCollectionDecorator<E, T extends ImmutableCollection<E>> implements ObservableCollectionCollection<E>
{
	private final Collection<E> internal;
	private transient ObservableCollectionSupport<E> support;

	public AbstractObservableCollectionDecorator(Collection<E> source, ObservableCollectionSupport<E> support)
	{
		this.internal = source;
		this.support = support;
	}

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
		Object oldSource = getSupport().copySource();
		action.execute(internal);
		getSupport().fireCollectionChangeEvent(oldSource, getSupport().copySource());
	}

	@Override
	public boolean add(E e)
	{
		final T oldValue = copyOf(internal);
		final boolean result = internal.add(e);
		getSupport().fireCollectionChangeEvent(oldValue, copyOf(internal));
		return result;
	}

	@Override
	public boolean remove(Object o)
	{
		Object oldSource = getSupport().copySource();
		final boolean result = internal.remove(o);
		getSupport().fireCollectionChangeEvent(oldSource, getSupport().copySource());
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		Object oldSource = getSupport().copySource();
		final boolean result = internal.addAll(c);
		getSupport().fireCollectionChangeEvent(oldSource, getSupport().copySource());
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		Object oldSource = getSupport().copySource();
		final boolean result = internal.retainAll(c);
		getSupport().fireCollectionChangeEvent(oldSource, getSupport().copySource());
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		Object oldSource = getSupport().copySource();
		final boolean result = internal.removeAll(c);
		getSupport().fireCollectionChangeEvent(oldSource, getSupport().copySource());
		return result;
	}

	@Override
	public void clear()
	{
		Object oldSource = getSupport().copySource();
		internal.clear();
		getSupport().fireCollectionChangeEvent(oldSource, getSupport().copySource());
	}

	@Override
	public void addCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getSupport().addCollectionChangeListener(listener);
	}

	@Override
	public void removeCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getSupport().removeCollectionChangeListener(listener);
	}

	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return getSupport().getCollectionChangeListeners();
	}

	@Override
	public String toString()
	{
		return internal.toString();
	}

	protected ObservableCollectionSupport<E> getSupport()
	{
		if (support == null)
		{
			this.support = new DefaultObservableCollectionSupport<E>(this);
		}

		return support;
	}

	protected abstract T copyOf(Collection<E> internal);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#copy()
	 */
	@Override
	public <T> T copyInternal()
	{
		return (T) copyOf(internal);
	}

}

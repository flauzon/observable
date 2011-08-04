package com.netappsid.observable;

import java.util.Collection;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

class ObservableSetDecorator<E> extends AbstractObservableCollectionDecorator<E> implements ObservableSet<E>
{
	private final Set<E> internal;

	ObservableSetDecorator(Set<E> source)
	{
		super(source);
		this.internal = source;
	}

	@Override
	public boolean add(E e)
	{
		final ImmutableSet<E> oldValue = ImmutableSet.copyOf(internal);
		final boolean result = internal.add(e);
		getSupport().fireCollectionChangeEvent(oldValue, ImmutableSet.copyOf(internal));
		return result;
	}

	@Override
	public boolean remove(Object o)
	{
		final ImmutableSet<E> oldValue = ImmutableSet.copyOf(internal);
		final boolean result = internal.remove(o);
		getSupport().fireCollectionChangeEvent(oldValue, ImmutableSet.copyOf(internal));
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		final ImmutableSet<E> oldValue = ImmutableSet.copyOf(internal);
		final boolean result = internal.addAll(c);
		getSupport().fireCollectionChangeEvent(oldValue, ImmutableSet.copyOf(internal));
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		final ImmutableSet<E> oldValue = ImmutableSet.copyOf(internal);
		final boolean result = internal.retainAll(c);
		getSupport().fireCollectionChangeEvent(oldValue, ImmutableSet.copyOf(internal));
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		final ImmutableSet<E> oldValue = ImmutableSet.copyOf(internal);
		final boolean result = internal.removeAll(c);
		getSupport().fireCollectionChangeEvent(oldValue, ImmutableSet.copyOf(internal));
		return result;
	}

	@Override
	public void clear()
	{
		final ImmutableSet<E> oldValue = ImmutableSet.copyOf(internal);
		internal.clear();
		getSupport().fireCollectionChangeEvent(oldValue, ImmutableSet.copyOf(internal));
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
}

package com.netappsid.observable;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import com.google.common.collect.ImmutableList;

class ObservableListDecorator<E> extends AbstractObservableCollectionDecorator<E> implements ObservableList<E>
{
	private final List<E> internal;

	ObservableListDecorator(List<E> source)
	{
		super(source);
		this.internal = source;
	}

	@Override
	public boolean add(E e)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		boolean result = internal.add(e);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal));
		return result;
	}

	@Override
	public void add(int index, E element)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		internal.add(index, element);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal), index);
	}

	@Override
	public boolean addAll(Collection<? extends E> c)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		boolean result = internal.addAll(c);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal));
		return result;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		boolean result = internal.addAll(index, c);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal), index);
		return result;
	}

	@Override
	public void clear()
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		internal.clear();
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal));
	}

	@Override
	public E get(int index)
	{
		return internal.get(index);
	}

	@Override
	public int indexOf(Object o)
	{
		return internal.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o)
	{
		return internal.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator()
	{
		return new ObservableListIterator<E>(internal.listIterator(), getSupport());
	}

	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new ObservableListIterator<E>(internal.listIterator(index), getSupport());
	}

	@Override
	public E remove(int index)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		final E element = internal.remove(index);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal), index);
		return element;
	}

	@Override
	public boolean remove(Object o)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		final int index = internal.indexOf(o);
		final boolean result = internal.remove(o);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal), index);
		return result;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		final boolean result = internal.removeAll(c);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal));
		return result;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		final boolean result = internal.retainAll(c);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal));
		return result;
	}

	@Override
	public E set(int index, E element)
	{
		final ImmutableList<E> oldList = ImmutableList.copyOf(internal);
		final E oldElement = internal.set(index, element);
		getSupport().fireCollectionChangeEvent(oldList, ImmutableList.copyOf(internal), index);
		return oldElement;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex)
	{
		return new ObservableListDecorator<E>(internal.subList(fromIndex, toIndex));
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
	public String toString()
	{
		return internal.toString();
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
		return ImmutableList.copyOf(getSupport().getCollectionChangeListeners());
	}
}

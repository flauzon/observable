package com.netappsid.observable.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.netappsid.observable.BatchAction;
import com.netappsid.observable.CollectionChangeListener;
import com.netappsid.observable.LazyInitializer;
import com.netappsid.observable.ObservableCollections;
import com.netappsid.observable.ObservableList;

public class LazyInitializerList<E> implements ObservableList<E>,Serializable
{

	private ObservableList<E> initilized;
	private final LazyInitializer<E> lazyInitializer;

	public LazyInitializerList(LazyInitializer<E> lazyInitializer)
	{
		this.lazyInitializer = lazyInitializer;
	}

	public void addCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getInitilized().addCollectionChangeListener(listener);
	}

	public void removeCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getInitilized().removeCollectionChangeListener(listener);
	}

	public int size()
	{
		return getInitilized().size();
	}

	public boolean isEmpty()
	{
		return getInitilized().isEmpty();
	}

	public boolean contains(Object o)
	{
		return getInitilized().contains(o);
	}

	public Iterator<E> iterator()
	{
		return getInitilized().iterator();
	}

	public Object[] toArray()
	{
		return getInitilized().toArray();
	}

	public <T> T[] toArray(T[] a)
	{
		return getInitilized().toArray(a);
	}

	public boolean add(E e)
	{
		return getInitilized().add(e);
	}

	public boolean remove(Object o)
	{
		return getInitilized().remove(o);
	}

	public boolean addAll(int index, Collection<? extends E> c)
	{
		return getInitilized().addAll(index, c);
	}

	public boolean containsAll(Collection<?> c)
	{
		return getInitilized().containsAll(c);
	}

	public boolean addAll(Collection<? extends E> c)
	{
		return getInitilized().addAll(c);
	}

	public boolean removeAll(Collection<?> c)
	{
		return getInitilized().removeAll(c);
	}

	public boolean retainAll(Collection<?> c)
	{
		return getInitilized().retainAll(c);
	}

	public void clear()
	{
		getInitilized().clear();
	}

	public boolean equals(Object o)
	{
		return getInitilized().equals(o);
	}

	public E get(int index)
	{
		return getInitilized().get(index);
	}

	public E set(int index, E element)
	{
		return getInitilized().set(index, element);
	}

	public void add(int index, E element)
	{
		getInitilized().add(index, element);
	}

	public int hashCode()
	{
		return getInitilized().hashCode();
	}

	public E remove(int index)
	{
		return getInitilized().remove(index);
	}

	public int indexOf(Object o)
	{
		return getInitilized().indexOf(o);
	}

	public int lastIndexOf(Object o)
	{
		return getInitilized().lastIndexOf(o);
	}

	public ListIterator<E> listIterator()
	{
		return getInitilized().listIterator();
	}

	public ListIterator<E> listIterator(int index)
	{
		return getInitilized().listIterator(index);
	}

	public List<E> subList(int fromIndex, int toIndex)
	{
		return getInitilized().subList(fromIndex, toIndex);
	}
	
	@Override
	public void executeBatchAction(BatchAction action)
	{
		getInitilized().executeBatchAction(action);
	}

	private ObservableList<E> getInitilized()
	{
		if (initilized == null)
		{
			List<E> newList = lazyInitializer.initialize();

			if (newList instanceof ObservableList)
			{
				initilized = (ObservableList<E>) newList;
			}
			else
			{
				initilized = ObservableCollections.decorateList(newList);
			}
		}

		return initilized;

	}
}

/**
 * 
 */
package com.netappsid.observable;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.netappsid.observable.internal.InternalObservableCollection;
import com.netappsid.observable.internal.MapObservableCollectionSupport;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class ObservableMapDecorator<K, E> implements ObservableMap<K, E>, InternalObservableCollection<E, Map<K, E>>
{
	private final Map<K, E> internal;
	private transient MapObservableCollectionSupport<K, E, ObservableMapDecorator<K, E>> support;

	ObservableMapDecorator(Map<K, E> source)
	{
		this.internal = source;
	}

	/**
	 * @return
	 * @see java.util.Map#size()
	 */
	@Override
	public int size()
	{
		return internal.size();
	}

	/**
	 * @return
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty()
	{
		return internal.isEmpty();
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key)
	{
		return internal.containsKey(key);
	}

	/**
	 * @param value
	 * @return
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value)
	{
		return internal.containsValue(value);
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public E get(Object key)
	{
		return internal.get(key);
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public E put(K key, E value)
	{
		getSupport().createSnapshot();
		E result = internal.put(key, value);
		getSupport().fireCollectionChangeEvent(key);
		return result;
	}

	/**
	 * @param key
	 * @return
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public E remove(Object key)
	{
		getSupport().createSnapshot();
		E result = internal.remove(key);
		getSupport().fireCollectionChangeEvent(key);
		return result;
	}

	/**
	 * @param m
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends E> m)
	{
		getSupport().createSnapshot();
		internal.putAll(m);
		getSupport().fireCollectionChangeEvent();
	}

	/**
	 * 
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear()
	{
		getSupport().createSnapshot();
		internal.clear();
		getSupport().fireCollectionChangeEvent();
	}

	/**
	 * @return
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet()
	{
		return new ObservableSetDecorator<K>(internal.keySet(), (InternalObservableCollectionSupport) getSupport());
	}

	/**
	 * @return
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<E> values()
	{
		return new ObservableCollectionDecorator(internal.values(), getSupport());
	}

	/**
	 * @return
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<K, E>> entrySet()
	{
		return new ObservableSetDecorator<Map.Entry<K, E>>(internal.entrySet(), (InternalObservableCollectionSupport) getSupport());
	}

	/**
	 * @param o
	 * @return
	 * @see java.util.Map#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o)
	{
		return internal.equals(o);
	}

	/**
	 * @return
	 * @see java.util.Map#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return internal.hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#executeBatchAction(com.netappsid.observable.BatchAction)
	 */
	@Override
	public void executeBatchAction(BatchAction action)
	{
		getSupport().createSnapshot();
		action.execute(internal);
		getSupport().fireCollectionChangeEvent();
	}

	@Override
	public MapObservableCollectionSupport<K, E, ObservableMapDecorator<K, E>> getSupport()
	{
		if (support == null)
		{
			this.support = new MapObservableCollectionSupport<K, E, ObservableMapDecorator<K, E>>(this);
		}

		return support;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#addCollectionChangeListener(com.netappsid.observable.CollectionChangeListener)
	 */
	@Override
	public void addCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getSupport().addCollectionChangeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#removeCollectionChangeListener(com.netappsid.observable.CollectionChangeListener)
	 */
	@Override
	public void removeCollectionChangeListener(CollectionChangeListener<E> listener)
	{
		getSupport().removeCollectionChangeListener(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#copy()
	 */
	@Override
	public Map<K, E> copyInternal()
	{
		return ImmutableMap.copyOf(internal);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.ObservableCollection#getCollectionChangeListeners()
	 */
	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return getSupport().getCollectionChangeListeners();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator()
	{
		return values().iterator();
	}
}

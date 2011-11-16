/**
 * 
 */
package com.netappsid.observable.internal;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.netappsid.observable.AbstractObservableCollectionSupport;
import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.ObservableMap;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class MapObservableCollectionSupport<K, E> extends AbstractObservableCollectionSupport<E, ImmutableMap<K, E>>
{

	/**
	 * @param source
	 */
	public MapObservableCollectionSupport(ObservableMap<K, E> source)
	{
		super(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionSupport#createCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	protected CollectionChangeEvent<E> createCollectionChangeEvent(ImmutableMap<K, E> oldMap, ImmutableMap<K, E> newMap, Object index)
	{
		MapDifference difference = Maps.difference(oldMap, newMap);
		com.netappsid.observable.internal.MapDifference mapDifference = new com.netappsid.observable.internal.MapDifference(difference);
		return new CollectionChangeEvent<E>(getSource(), mapDifference, index);
	}

}

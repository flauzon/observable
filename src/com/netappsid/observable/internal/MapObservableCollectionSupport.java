/**
 * 
 */
package com.netappsid.observable.internal;

import java.util.Map;

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
public class MapObservableCollectionSupport<K, V, T extends ObservableMap<K, V> & InternalObservableCollection<Map.Entry<K, V>, Map<K, V>>> extends
		AbstractObservableCollectionSupport<Map.Entry<K, V>, Map<K, V>>
{

	/**
	 * @param source
	 */
	public MapObservableCollectionSupport(T source)
	{
		super(source);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.netappsid.observable.AbstractObservableCollectionSupport#createCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	protected CollectionChangeEvent<Map.Entry<K, V>> createCollectionChangeEvent(Object index)
	{
		MapDifference difference = Maps.difference(getSnapshot(), takeSnapshot());
		com.netappsid.observable.internal.MapDifference mapDifference = new com.netappsid.observable.internal.MapDifference(difference);
		return newCollectionChangeEvent(mapDifference, index);
	}

}

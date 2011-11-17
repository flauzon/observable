/**
 * 
 */
package com.netappsid.observable.internal;

import com.netappsid.observable.ObservableCollection;
import com.netappsid.observable.ObservableCollectionSupport;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public interface InternalObservableCollection<E, T> extends ObservableCollection<E>
{
	T copyInternal();

	/**
	 * @return
	 */
	ObservableCollectionSupport<E, T> getSupport();
}

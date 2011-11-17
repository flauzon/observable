package com.netappsid.observable;

import com.netappsid.observable.internal.DefaultObservableCollectionSupport;
import com.netappsid.observable.internal.InternalObservableCollection;

public class StandardObservableCollectionSupportFactory implements ObservableCollectionSupportFactory
{
	@Override
	public <E> ObservableCollectionSupport<E> newObservableCollectionSupport(ObservableCollection<E> source)
	{
		if (source instanceof InternalObservableCollection)
		{
			return ((InternalObservableCollection) source).getSupport();
		}
		else
		{
			return new DefaultObservableCollectionSupport<E, ObservableCollection<E>>(source);
		}
	}
}
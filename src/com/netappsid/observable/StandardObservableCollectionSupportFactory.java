package com.netappsid.observable;

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
			throw new IllegalArgumentException("Is not a valid ObservableCollection");
		}
	}
}
package com.netappsid.observable;

public class StandardObservableCollectionSupportFactory implements ObservableCollectionSupportFactory
{
	@Override
	public <E> ObservableCollectionSupport<E> newObservableCollectionSupport(ObservableCollection<E> source)
	{
		return new DefaultObservableCollectionSupport<E>(source);
	}
}

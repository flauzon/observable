package com.netappsid.observable;

public interface ObservableCollectionSupportFactory
{
	<E, T> ObservableCollectionSupport<E, T> newObservableCollectionSupport(ObservableCollection<E> source);
}

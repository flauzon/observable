package com.netappsid.observable.test;

import static org.junit.Assert.*;

import java.util.Collection;

import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.CollectionChangeListener;
import com.netappsid.observable.ObservableCollection;

public class CollectionChangeEventSpy implements CollectionChangeListener<Integer>
{
	private CollectionChangeEvent event = null;

	@Override
	public void onCollectionChange(CollectionChangeEvent event)
	{
		this.event = event;
	}

	public CollectionChangeEvent getEvent()
	{
		return event;
	}

	public void assertEvent(ObservableCollection source, Collection added, Collection removed)
	{
		assertEquals("source", source, event.getSource());
		assertEquals("added", added, event.getAdded());
		assertEquals("removed", removed, event.getRemoved());
	}

	public void assertEvent(ObservableCollection source, Collection added, Collection removed, Object index)
	{
		assertEquals("source", source, event.getSource());
		assertEquals("added", added, event.getAdded());
		assertEquals("removed", removed, event.getRemoved());
		assertEquals("index", index, event.getIndex());
	}
}
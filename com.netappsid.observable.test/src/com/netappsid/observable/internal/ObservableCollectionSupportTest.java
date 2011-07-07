package com.netappsid.observable.internal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.netappsid.observable.CollectionChangeEvent;
import com.netappsid.observable.CollectionChangeListener;
import com.netappsid.observable.DefaultObservableCollectionSupport;
import com.netappsid.observable.ListDifference;
import com.netappsid.observable.ObservableCollectionSupport;
import com.netappsid.observable.ObservableCollections;
import com.netappsid.observable.ObservableSet;
import com.netappsid.test.tools.CollectionChangeEventSpy;

public class ObservableCollectionSupportTest
{
	private ObservableSet<Integer> source;
	private ObservableCollectionSupport<Integer> support;

	@Before
	public void before()
	{
		source = ObservableCollections.newObservableHashSet();
		support = new DefaultObservableCollectionSupport<Integer>(source);
	}

	@Test
	public void test_canAddListener()
	{
		final CollectionChangeListener listener = mock(CollectionChangeListener.class);
		support.addCollectionChangeListener(listener);

		assertTrue(support.getCollectionChangeListeners().containsKey(listener));
	}

	@Test
	public void test_canRemoveListener()
	{
		final CollectionChangeListener listener = mock(CollectionChangeListener.class);
		support.addCollectionChangeListener(listener);
		support.removeCollectionChangeListener(listener);

		assertFalse(support.getCollectionChangeListeners().containsKey(listener));
	}

	@Test
	public void test_createANewNonIndexedCollectionChangeEvent()
	{
		final ImmutableList<Integer> added = ImmutableList.of(1);
		final ImmutableList<Integer> removed = ImmutableList.of(2);

		final CollectionChangeEvent event = support.newCollectionChangeEvent(new ListDifference(removed, added));
		assertEquals(source, event.getSource());
		assertEquals(added, event.getAdded());
		assertEquals(removed, event.getRemoved());
	}

	@Test
	public void test_createAnIndexedCollectionChangeEvent()
	{
		final ImmutableList<Integer> added = ImmutableList.of(1);
		final ImmutableList<Integer> removed = ImmutableList.of(2);
		final int index = 1;

		final CollectionChangeEvent event = support.newCollectionChangeEvent(new ListDifference(removed, added), index);
		assertEquals(source, event.getSource());
		assertEquals(added, event.getAdded());
		assertEquals(removed, event.getRemoved());
		assertEquals(index, event.getIndex());
	}

	@Test
	public void test_firesCollectionChangeEventFromSetsDifference()
	{
		final CollectionChangeEventSpy eventSpy = new CollectionChangeEventSpy();
		support.addCollectionChangeListener(eventSpy);

		support.fireCollectionChangeEvent(ImmutableSet.of(1, 2, 3), ImmutableSet.of(2, 3, 4));

		eventSpy.assertEvent(source, ImmutableSet.of(4), ImmutableSet.of(1));
	}

	@Test
	public void test_firesCollectionChangeEventFromListsDifference()
	{
		final CollectionChangeEventSpy eventSpy = new CollectionChangeEventSpy();
		support.addCollectionChangeListener(eventSpy);

		support.fireCollectionChangeEvent(ImmutableList.of(1, 2, 3), ImmutableList.of(2, 3, 4));

		eventSpy.assertEvent(source, ImmutableList.of(4), ImmutableList.of(1));
	}

	@Test
	public void test_firesIndexedCollectionChangeEventFromListsDifference()
	{
		final CollectionChangeEventSpy eventSpy = new CollectionChangeEventSpy();
		support.addCollectionChangeListener(eventSpy);

		support.fireCollectionChangeEvent(ImmutableList.of(1, 2, 3), ImmutableList.of(2, 3, 4), 2);

		eventSpy.assertEvent(source, ImmutableList.of(4), ImmutableList.of(1), 2);
	}

	@Test
	public void test_firesACollectionChangeEventPassedInParameter()
	{
		final CollectionChangeEventSpy eventSpy = new CollectionChangeEventSpy();
		support.addCollectionChangeListener(eventSpy);

		final CollectionChangeEvent<Integer> event = support.newCollectionChangeEvent(new SetDifference(ImmutableSet.of(2), ImmutableSet.of(1)));
		support.fireCollectionChangeEvent(event);

		assertEquals(event, eventSpy.getEvent());
	}

	@Test
	public void test_doesNotFireWhenEventHasNoAddedOrRemoved()
	{
		final CollectionChangeEventSpy eventSpy = new CollectionChangeEventSpy();
		support.addCollectionChangeListener(eventSpy);

		final CollectionChangeEvent<Integer> event = support.newCollectionChangeEvent(new SetDifference(ImmutableSet.<Integer> of(), ImmutableSet.<Integer> of()));
		support.fireCollectionChangeEvent(event);

		assertNull(eventSpy.getEvent());
	}
}

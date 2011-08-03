package com.netappsid.observable;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StandardObservableCollectionSupportFactoryTest
{
	private StandardObservableCollectionSupportFactory factory;

	@Before
	public void before()
	{
		factory = new StandardObservableCollectionSupportFactory();
	}

	@Test
	public void test_configuresSourceObservableCollection()
	{
		final ObservableCollection<String> source = ObservableCollections.newObservableArrayList();
		final ObservableCollectionSupport<String> support = factory.newObservableCollectionSupport(source);

		final CollectionChangeEvent event = support.newCollectionChangeEvent(null);
		assertEquals(source, event.getSource());
	}
}

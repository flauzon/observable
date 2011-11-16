/**
 * 
 */
package com.netappsid.observable;

import static com.netappsid.observable.ObservableCollections.*;

import java.util.Collection;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableMap;
import com.netappsid.observable.test.CollectionChangeEventSpy;

/**
 * @author xjodoin
 * @author NetAppsID inc.
 * 
 * @version
 * 
 */
public class ObservableMapDecoratorTest
{

	private ObservableMap<Integer, Integer> observableMap;
	private CollectionChangeEventSpy listener;

	@Before
	public void before()
	{
		observableMap = newObservableHashMap();
		observableMap.put(1, 1);
		observableMap.put(2, 2);
		observableMap.put(3, 3);

		this.listener = new CollectionChangeEventSpy();
		observableMap.addCollectionChangeListener(listener);
	}

	@Test
	public void test_putOneElement()
	{
		observableMap.put(4, 4);
		listener.assertEvent(observableMap, ImmutableMap.of(4, 4).entrySet(), ImmutableMap.of().entrySet());
	}

	@Test
	public void test_RemoveElement()
	{
		observableMap.remove(1);
		listener.assertEvent(observableMap, ImmutableMap.of().entrySet(), ImmutableMap.of(1, 1).entrySet(), 1);
	}

	@Test
	public void test_PutOnExistingKey()
	{
		observableMap.put(3, 7);
		listener.assertEvent(observableMap, ImmutableMap.of().entrySet(), ImmutableMap.of().entrySet(), 3);
	}

	@Test
	public void test_removeWithValueIterator()
	{
		Collection<Integer> values = observableMap.values();
		values.remove(1);
		listener.assertEvent(observableMap, ImmutableMap.of().entrySet(), ImmutableMap.of(1, 1).entrySet());
	}

	@Test
	public void test_removeWithKeySetIterator()
	{
		Set<Integer> keySet = observableMap.keySet();
		keySet.remove(1);
		listener.assertEvent(observableMap, ImmutableMap.of().entrySet(), ImmutableMap.of(1, 1).entrySet());
	}

	@Test
	public void test_clear()
	{
		ImmutableMap copy = observableMap.copyInternal();
		observableMap.clear();
		listener.assertEvent(observableMap, ImmutableMap.of().entrySet(), copy.entrySet());
	}

}

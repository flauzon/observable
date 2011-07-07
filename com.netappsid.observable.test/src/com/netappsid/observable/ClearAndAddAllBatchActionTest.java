package com.netappsid.observable;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

public class ClearAndAddAllBatchActionTest
{
	@Test
	public void testBatchAction()
	{
		Collection newCollection = Arrays.asList("1", "2");

		ClearAndAddAllBatchAction action = new ClearAndAddAllBatchAction(newCollection);

		ArrayList<Object> source = spy(new ArrayList<Object>());
		ObservableList<Object> targetCollection = ObservableCollections.decorateList(source);
		targetCollection.executeBatchAction(action);

		verify(source).clear();
		verify(source).addAll(eq(newCollection));
	}
}

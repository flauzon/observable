package com.netappsid.observable;

import static com.google.common.base.Objects.*;

import java.util.Collection;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;

public class CollectionChangeEvent<E>
{
	private final ObservableCollection<E> source;
	private final int index;
	private final CollectionDifference<E> difference;

	public CollectionChangeEvent(ObservableCollection<E> source, CollectionDifference<E> difference)
	{
		this(source, difference, -1);
	}

	public CollectionChangeEvent(ObservableCollection<E> source, CollectionDifference<E> difference, int index)
	{
		this.source = source;
		this.difference = difference;
		this.index = index;
	}

	public ObservableCollection<E> getSource()
	{
		return source;
	}

	public CollectionDifference<E> getDifference()
	{
		return difference;
	}

	public int getIndex()
	{
		return index;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		else if (obj instanceof CollectionChangeEvent)
		{
			final CollectionChangeEvent event = (CollectionChangeEvent) obj;
			return equal(source, event.source) && equal(difference, event.difference) && equal(index, event.index);
		}
		else
		{
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(source, difference, index);
	}

	@Override
	public String toString()
	{
		final ToStringHelper toStringHelper = Objects.toStringHelper(this);
		toStringHelper.add("source", source);
		toStringHelper.add("difference", difference);
		toStringHelper.add("index", index);
		return toStringHelper.toString();
	}

	public Collection<E> getAdded()
	{
		return difference.getAdded();
	}

	public Collection<E> getRemoved()
	{
		return difference.getRemoved();
	}
}

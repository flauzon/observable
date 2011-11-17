package com.netappsid.observable;

import java.awt.EventQueue;

import org.apache.log4j.Logger;

import com.google.common.collect.ImmutableList;

public class SwingObservableCollectionSupport<E, T> implements ObservableCollectionSupport<E, T>
{
	private static final Logger LOGGER = Logger.getLogger(SwingObservableCollectionSupport.class);
	private final ObservableCollectionSupport<E, T> delegate;

	public SwingObservableCollectionSupport(ObservableCollectionSupport<E, T> delegate)
	{
		this.delegate = delegate;
	}

	@Override
	public void fireCollectionChangeEvent(final CollectionChangeEvent<E> event)
	{
		if (EventQueue.isDispatchThread())
		{
			delegate.fireCollectionChangeEvent(event);
		}
		else
		{
			try
			{
				EventQueue.invokeAndWait(new Runnable()
					{
						@Override
						public void run()
						{
							delegate.fireCollectionChangeEvent(event);
						}
					});
			}
			catch (Exception e)
			{
				LOGGER.error(e.getMessage(), e);
			}
		}
	}

	/**
	 * @param listener
	 * @see com.netappsid.observable.ObservableCollectionSupport#addCollectionChangeListener(com.netappsid.observable.CollectionChangeListener)
	 */
	@Override
	public void addCollectionChangeListener(CollectionChangeListener listener)
	{
		delegate.addCollectionChangeListener(listener);
	}

	/**
	 * @param listener
	 * @see com.netappsid.observable.ObservableCollectionSupport#removeCollectionChangeListener(com.netappsid.observable.CollectionChangeListener)
	 */
	@Override
	public void removeCollectionChangeListener(CollectionChangeListener listener)
	{
		delegate.removeCollectionChangeListener(listener);
	}

	/**
	 * @return
	 * @see com.netappsid.observable.ObservableCollectionSupport#getCollectionChangeListeners()
	 */
	@Override
	public ImmutableList<CollectionChangeListener<E>> getCollectionChangeListeners()
	{
		return delegate.getCollectionChangeListeners();
	}

	/**
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent()
	 */
	@Override
	public void fireCollectionChangeEvent()
	{
		delegate.fireCollectionChangeEvent();
	}

	/**
	 * @param index
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(java.lang.Object)
	 */
	@Override
	public void fireCollectionChangeEvent(Object index)
	{
		delegate.fireCollectionChangeEvent(index);
	}

	/**
	 * @return
	 * @see com.netappsid.observable.ObservableCollectionSupport#createSnapshot()
	 */
	@Override
	public void createSnapshot()
	{
		delegate.createSnapshot();
	}

	/**
	 * @param difference
	 * @return
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference)
	{
		return delegate.newCollectionChangeEvent(difference);
	}

	/**
	 * @param difference
	 * @param index
	 * @return
	 * @see com.netappsid.observable.ObservableCollectionSupport#newCollectionChangeEvent(com.netappsid.observable.CollectionDifference, java.lang.Object)
	 */
	@Override
	public CollectionChangeEvent<E> newCollectionChangeEvent(CollectionDifference<E> difference, Object index)
	{
		return delegate.newCollectionChangeEvent(difference, index);
	}
}

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
	 * @param oldCollection
	 * @param newCollection
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void fireCollectionChangeEvent(T oldCollection, T newCollection)
	{
		delegate.fireCollectionChangeEvent(oldCollection, newCollection);
	}

	/**
	 * @param oldCollection
	 * @param newCollection
	 * @param index
	 * @see com.netappsid.observable.ObservableCollectionSupport#fireCollectionChangeEvent(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public void fireCollectionChangeEvent(T oldCollection, T newCollection, Object index)
	{
		delegate.fireCollectionChangeEvent(oldCollection, newCollection, index);
	}

	/**
	 * @return
	 * @see com.netappsid.observable.ObservableCollectionSupport#copySource()
	 */
	@Override
	public T copySource()
	{
		return delegate.copySource();
	}
}

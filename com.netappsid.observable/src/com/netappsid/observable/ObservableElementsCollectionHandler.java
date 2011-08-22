package com.netappsid.observable;

import java.beans.PropertyChangeListener;

/**
 * Implementation of ObservableListListener that manages registration of a PropertyChangeListener on the elements of the list.
 * 
 * TODO: Add the functionality of managing registration of more then one PropertyChangeListener if necessary. TODO: Class is coupled with
 * com.netappsid.bo.model.Model. See if there is a way to remove this coupling.
 * 
 * @author Eric Bélanger
 * @author NetAppsID Inc.
 */
public class ObservableElementsCollectionHandler<T extends ObservableByName> implements CollectionChangeListener<T>
{
	private final PropertyChangeListener elementListener;
	private final String propertyName;

	public ObservableElementsCollectionHandler(PropertyChangeListener elementListener)
	{
		this(null, elementListener);
	}

	public ObservableElementsCollectionHandler(String propertyName, PropertyChangeListener elementListener)
	{
		assert elementListener != null;
		this.elementListener = elementListener;
		this.propertyName = propertyName;
	}

	/**
	 * Registers itself to the specified ObservableCollection and registers the PropertyChangeListener on all elements present in the ObservableCollection.
	 */
	public void install(ObservableCollection<T> observableCollection)
	{
		observableCollection.addCollectionChangeListener(this);

		for (T element : observableCollection)
		{
			addElementListener(element);
		}
	}

	/**
	 * Unregisters itself to the specified ObservableCollection and unregisters the PropertyChangeListener on all elements present in the ObservableCollection.
	 */
	public void uninstall(ObservableCollection<T> observableCollection)
	{
		observableCollection.removeCollectionChangeListener(this);

		for (T element : observableCollection)
		{
			removeElementListener(element);
		}
	}

	@Override
	public void onCollectionChange(CollectionChangeEvent<T> event)
	{
		for (T element : event.getAdded())
		{
			addElementListener(element);
		}

		for (T element : event.getRemoved())
		{
			removeElementListener(element);
		}
	}

	private void addElementListener(T element)
	{
		if (propertyName != null)
		{
			element.addPropertyChangeListener(propertyName, elementListener);
		}
		else
		{
			element.addPropertyChangeListener(elementListener);
		}
	}

	private void removeElementListener(T element)
	{
		if (propertyName != null)
		{
			element.removePropertyChangeListener(propertyName, elementListener);
		}
		else
		{
			element.removePropertyChangeListener(elementListener);
		}
	}
}

package com.netappsid.observable;

import java.util.Collection;

import com.netappsid.validate.Validate;

public class ClearAndAddAllBatchAction implements BatchAction<Collection>
{
	private Collection newCollection;

	public ClearAndAddAllBatchAction(Collection newCollection)
	{
		this.newCollection = newCollection;
	}
	
	@Override
	public void execute(Collection collection)
	{
		collection.clear();
		
		if (this.newCollection != null)
		{
			collection.addAll(this.newCollection);
		}
	}
}

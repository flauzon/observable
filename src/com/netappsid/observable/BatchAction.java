package com.netappsid.observable;

import java.util.Collection;

public interface BatchAction<T extends Collection<?>>
{
	void execute(T collection);
}

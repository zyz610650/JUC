package com.juc.juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author: @zyz
 */
public class MySync extends AbstractQueuedSynchronizer {

	public MySync () {
	}

	@Override
	protected boolean tryAcquire (int arg) {
		if(arg==1)
		{
			if (compareAndSetState (0,1))
			{
				setExclusiveOwnerThread (Thread.currentThread ());
				return true;
			}
		}
		return false;
	}

	@Override
	protected boolean tryRelease (int arg) {
		if (arg==1)
		{
			if (getState ()==0)
			{
				throw new IllegalMonitorStateException ();
			}
			setExclusiveOwnerThread (null);
			setState (0);
			return true;
		}
		return false;
	}

	@Override
	protected boolean isHeldExclusively () {
		return super.isHeldExclusively ();
	}
}


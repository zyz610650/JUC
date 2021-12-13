package com.juc.juc.aqs;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: @zyz
 */
public class MyLock implements Lock {

	private MySync mySync=new MySync ();
	@Override
	public void lock () {
		mySync.acquire (1);
	}

	@Override
	public void lockInterruptibly () throws InterruptedException {
			mySync.acquireInterruptibly (1);
	}

	@Override
	public boolean tryLock () {
		return mySync.tryAcquire (1);
	}

	@Override
	public boolean tryLock (long time , TimeUnit unit) throws InterruptedException {
		return mySync.tryAcquireNanos (1,unit.toNanos (time));
	}

	@Override
	public void unlock () {
		mySync.release (1);
	}

	@Override
	public Condition newCondition () {
		return null;
	}
}

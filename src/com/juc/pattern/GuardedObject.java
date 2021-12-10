package com.juc.pattern;

/**
 * @author: @zyz
 */
public class GuardedObject {

	private Object obj=null;

	public Object get(){
		synchronized (this)
		{
			while (obj==null)
			{
				try {
					this.wait ();
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
		}
		return obj;

	}

	public void complete(Object res)
	{
		synchronized (this)
		{
			obj=res;
			this.notifyAll ();
		}
	}

}

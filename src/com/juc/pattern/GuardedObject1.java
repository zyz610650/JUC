package com.juc.pattern;

/**
 * @author: @zyz
 */
public class GuardedObject1 {

	private Object obj=null;

	public Object get(long timeout){

		synchronized (this)
		{
			Thread thread=new Thread (new Runnable () {
				@Override
				public void run () {

				}
			});
			thread.start ();
			long beginTime=System.currentTimeMillis ();
			long passtime=0;
			long waitTime;
			while (obj==null)
			{
				waitTime=timeout-passtime;
				if(waitTime<=0) break;
				try {
					this.wait (waitTime);
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
				passtime=System.currentTimeMillis ()-beginTime;
			}
		}
		return obj;

	}

	public void complete(Object res)
	{

	}

}

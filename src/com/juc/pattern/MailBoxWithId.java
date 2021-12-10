package com.juc.pattern;

/**
 * @author: @zyz
 */
public class MailBoxWithId {

	private int id;
	private String msg;

	public MailBoxWithId (int id , String msg) {
		this.id = id;
		this.msg = msg;


	}

	public MailBoxWithId (int id) {
		this.id = id;
	}

	public String get(long timeout)
	{
		synchronized (this)
		{
			long beginTime=System.currentTimeMillis ();
			long passtime=0;
			long waittime=0;
			while (msg==null)
			{
				waittime=timeout-passtime;
				if (waittime<=0) break;
				try {
					this.wait (waittime);
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
			passtime=System.currentTimeMillis ()-beginTime;
		}
		return msg;
	}

	public void complete(String msg)
	{
		synchronized (this)
		{
			this.msg=msg;
			this.notifyAll ();
		}
	}

}

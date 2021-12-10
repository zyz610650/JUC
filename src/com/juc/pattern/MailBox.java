package com.juc.pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: @zyz
 */
public class MailBox {

	Map <Integer,MailBoxWithId> map=new ConcurrentHashMap <> ();
	private AtomicInteger id=new AtomicInteger (0);
	public MailBoxWithId createMaiBox()
	{
		int id=getId ();
		MailBoxWithId mailBoxWithId=new MailBoxWithId (id);
		map.put (id,mailBoxWithId);
		return mailBoxWithId;
	}

	public MailBoxWithId getMailBox(int id)
	{
		return map.remove (id);
	}

	private  int getId()
	{
		return id.getAndIncrement ();
	}

}

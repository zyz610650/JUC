package com.juc.pattern;

/**
 * @author: @zyz
 */
public class People extends Thread{

	static private MailBox mailBox=new MailBox ();
	@Override
	public void run () {

		MailBoxWithId mailBoxWithId=mailBox.createMaiBox ();
		mailBoxWithId.get (1000);
	}
}

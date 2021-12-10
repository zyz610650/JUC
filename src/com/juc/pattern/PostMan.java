package com.juc.pattern;

/**
 * @author: @zyz
 */
public class PostMan extends Thread{
	private int id;
	private String msg;
	static private MailBox mailBox=new MailBox ();

	public PostMan (int id , String msg) {
		this.id = id;
		this.msg = msg;
	}

	@Override
	public void run () {

		MailBoxWithId mailBoxWithId=mailBox.getMailBox (id);
		mailBoxWithId.complete (msg);

	}
}

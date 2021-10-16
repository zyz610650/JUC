package com.juc.patern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: @zyz
 */
@Slf4j
public class SequentialControlPattern_WAIT_NOTIFY_DEMO1 {

	static boolean flag=true;
	public static void main (String[] args) {
		Object lock=new Object ();

		Thread t1=new Thread (()->{
			synchronized (lock)
			{
				while (flag)
				{
					try {
						lock.wait ();
					} catch (InterruptedException e) {
						e.printStackTrace ();
					}
				}
				log.debug ("1");
			}
		});
		t1.start ();

		Thread t2=new Thread (()->{
			synchronized (lock)
			{
				log.debug ("2");
				flag=false;
				lock.notifyAll ();
			}
		});
		t2.start ();
	}
}

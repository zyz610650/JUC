package com.juc.patern;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: @zyz
 */
@Slf4j
public class SequentialControlPattern_WAIT_NOTIFY_DEMO2 {

	static Object lock=new Object ();

	static int wait=1;
	public static void main (String[] args) {
		new Thread (()->{
			f ("A",1,2);
		}).start ();
		new Thread (()->{
			f ("B",2,3);
		}).start ();
		new Thread (()->{
			f ("C",3,1);
		}).start ();


	}

	@SneakyThrows
	public static void f(String str, int cnt, int next)
	{
		for (int i=0;i<5;i++)
		{
			synchronized (lock)
			{
				while(cnt!=wait)
				{
					lock.wait ();
				}
				wait=next;
				System.out.print(str);
				lock.notifyAll ();
			}
		}
	}

}

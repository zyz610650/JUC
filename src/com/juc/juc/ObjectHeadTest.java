package com.juc.juc;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author: @zyz
 */
@Slf4j
public class ObjectHeadTest {
	public static void main (String[] args) throws InterruptedException {
		Thread.sleep (5000);
		ObjectHeadTest objectHeadTest=new ObjectHeadTest ();
//		log.debug ("加锁前");
//		log.debug (ClassLayout.parseInstance (objectHeadTest).toPrintable ());
//		log.debug ("等待加载偏向锁");

		log.debug ("偏向锁");
		log.debug (ClassLayout.parseInstance (objectHeadTest).toPrintable ());
		Thread thread1=new Thread (()->{
			synchronized (objectHeadTest)
			{
				System.out.println (("线程1: 进入synchronized"));
				System.out.println (ClassLayout.parseInstance (objectHeadTest).toPrintable ());
				try {
					Thread.sleep (2000);
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
		});
		thread1.start ();
		Thread.sleep (2000);
		Thread thread2=new Thread (()->{
			synchronized (objectHeadTest)
			{
				System.out.println (("线程2: 进入synchronized"));
				System.out.println (ClassLayout.parseInstance (objectHeadTest).toPrintable ());
				try {
					Thread.sleep (2000);
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
		});
		thread2.start ();
		Thread.sleep (1000);
		synchronized (objectHeadTest)
		{
			System.out.println (("main线程: 进入synchronized"));
			System.out.println (ClassLayout.parseInstance (objectHeadTest).toPrintable ());
		}
		System.out.println ("退出锁");
		Thread.sleep (5000);
		System.out.println ("当前处于无锁状态: ");
		System.out.println (ClassLayout.parseInstance (objectHeadTest).toPrintable ());
		System.out.println ("再次进入轻量级锁: ");
		Thread thread3=new Thread (()->{
			synchronized (objectHeadTest)
			{
				System.out.println (("线程3: 进入synchronized"));
				System.out.println (ClassLayout.parseInstance (objectHeadTest).toPrintable ());


			}
		});
		thread3.start ();
		Thread.sleep (5000);
		System.out.println (ClassLayout.parseInstance (objectHeadTest).toPrintable ());
		synchronized (objectHeadTest)
		{
			System.out.println (ClassLayout.parseInstance (objectHeadTest).toPrintable ());
		}

	}
}

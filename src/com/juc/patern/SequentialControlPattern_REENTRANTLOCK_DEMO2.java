package com.juc.patern;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: @zyz
 */
@Slf4j
public class SequentialControlPattern_REENTRANTLOCK_DEMO2 {
	static  Thread t1;
	static Thread t2;
	static Thread t3;
	static ReentrantLock lock=new ReentrantLock();
	public static void main (String[] args) {


		Condition a=lock.newCondition ();
		Condition b=lock.newCondition ();
		Condition c=lock.newCondition ();

		t1=new Thread (()->{
			f("A",a,b);
		});



		t2=new Thread (()->{
			f("B",b,c);
		});


		t3=new Thread (()->{
			f("C",c,a);
		});
		t1.start ();
		t2.start ();
		t3.start ();

		// 不加休眠的画 t1线程会先 a.await
		// 再 a.signal()
		// 他没有park/unpark那样的机制

//		try {
//			Thread.sleep (1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace ();
//		}
		lock.lock ();
		try {
			a.signal ();
		}finally {
			lock.unlock ();
		}

	}

	static void f(String str,Condition cnt,Condition next)
	{
		for (int i=0;i<5;i++)
		{
			lock.lock ();
			try {
				try {
					cnt.await ();
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
				System.out.print (str);
				next.signal ();
			}finally {

				lock.unlock ();
			}
		}
	}
}

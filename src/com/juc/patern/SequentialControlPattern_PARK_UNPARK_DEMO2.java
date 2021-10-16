package com.juc.patern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: @zyz
 */
@Slf4j
public class SequentialControlPattern_PARK_UNPARK_DEMO2 {

	static  Thread t1;
	static Thread t2;
	static Thread t3;
	static Thread wait;
	public static void main (String[] args) {
		t1=new Thread (()->{
			f("A",t2);
		});



		t2=new Thread (()->{
			f("B",t3);
		});


		t3=new Thread (()->{
			f("C",t1);
		});
		t1.start ();
		t2.start ();
		t3.start ();
		LockSupport.unpark (t1);
	}

	public static void f(String str,Thread next)
	{
		for (int i=0;i<5;i++)
		{
			LockSupport.park ();
			System.out.print (str);
			LockSupport.unpark (next);
		}
	}

}

package com.juc.patern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: @zyz
 */
@Slf4j
public class SequentialControlPattern_PARK_UNPARK_DEMO1 {


	public static void main (String[] args) {
		Object lock=new Object ();

		Thread t1=new Thread (()->{

				LockSupport.park ();
				log.debug ("1");

		});
		t1.start ();

		Thread t2=new Thread (()->{
				log.debug ("2");
				LockSupport.unpark (t1);

		});
		t2.start ();
	}
}

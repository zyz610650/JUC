package com.juc.patern;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: @zyz
 */
@Slf4j
public class SequentialControlPattern_REENTRANTLOCK_DEMO1 {


	public static void main (String[] args) {
		ReentrantLock lock=new ReentrantLock ();
		Condition c1=lock.newCondition ();

		Thread t1=new Thread (()->{

			try {
				c1.await ();
			} catch (InterruptedException e) {
				e.printStackTrace ();
			}
			log.debug ("1");

		});
		t1.start ();

		Thread t2=new Thread (()->{
				log.debug ("2");

				c1.signal ();

		});
		t2.start ();
	}
}

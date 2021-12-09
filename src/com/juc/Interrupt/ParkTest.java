package com.juc.Interrupt;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: @zyz
 */
public class ParkTest {
	public static void main (String[] args) throws InterruptedException {
		Thread thread = new Thread (() -> {

			LockSupport.park ();
			System.out.println ("打断");

		});
		thread.start ();

		System.out.println ("进入休眠");
		Thread.sleep (1000);
		System.out.println ("休眠结束");
		thread.interrupt ();
	}
}

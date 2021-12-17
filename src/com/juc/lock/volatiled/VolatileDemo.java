package com.juc.lock.volatiled;

/**
 * @author: @zyz
 */
public class VolatileDemo {
	static   volatile int conunt=0;

	public static void main (String[] args) throws InterruptedException {
		Thread thread1 = new Thread (() -> {
			for (int i = 0 ; i < 10000 ; i++) {
				conunt++;
			}
		});
		Thread thread = new Thread (() -> {
			for (int i = 0 ; i < 10000 ; i++) {
				conunt++;
			}
		});
		thread.start ();
		thread1.start ();
		thread.join ();
		thread1.join ();
		System.out.println (conunt);
	}
}

package com.juc.juc;

/**
 * @author: @zyz
 */
public class SynchronizedTest {

	static final Object lock=new Object();
	static int counter = 0;
	public static void main(String[] args) {
		synchronized (lock) {
			counter++;
		}
	}
}

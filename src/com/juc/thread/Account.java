package com.juc.thread;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: @zyz
 */
public class Account {
	public static void main (String[] args) {
		demo ();
		String str=new String ("a");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	}
	static void demo() {
		List <Thread> ts = new ArrayList <> ();
		long start = System.nanoTime();
		for (int i = 0; i < 1000; i++) {
			ts.add(new Thread(() -> {
				try {
					Thread.sleep (500);
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}));
		}
		ts.forEach(Thread::start);
		ts.forEach(t -> {
			try {
				System.out.println ("1");
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		long end = System.nanoTime();

	}
}

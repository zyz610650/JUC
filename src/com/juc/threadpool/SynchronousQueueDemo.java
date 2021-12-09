package com.juc.threadpool;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SynchronousQueue;

/**
 * @author: @zyz
 */
@Slf4j
public class SynchronousQueueDemo {


	@SneakyThrows
	public static void main (String[] args) {
		SynchronousQueue <Integer> integers = new SynchronousQueue<>();
		new Thread(() -> {
			try {
				log.debug("putting {} ", 1);
				integers.put(1);
				log.debug("{} putted...", 1);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t1").start();
		new Thread(() -> {
			try {

				log.debug("putting...{} ", 2);
				integers.put(2);
				log.debug("{} putted...", 2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t1").start();
		Thread.sleep (2000);
		new Thread(() -> {
			try {
				log.debug("taking {}", 1);
				integers.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t2").start();
		Thread.sleep (2000);
		new Thread(() -> {
			try {
				log.debug("taking {}", 2);
				integers.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		},"t3").start();
	}
}

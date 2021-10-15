package com.juc.lock;

/**
 * @author: @zyz
 */
public class BiasedLock {

	public static void main (String[] args) {
	Dog d=new Dog ();
	new Thread (()->{
		synchronized (d){
			System.out.println ();
		}
		synchronized (BiasedLock.class)
		{
			BiasedLock.class.notify ();
		}
	},"t1").start ();


		new Thread (()->{

			synchronized (BiasedLock.class)
			{
				try {
					BiasedLock.class.wait ();
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
		},"t2").start ();
}
}

class Dog{

}

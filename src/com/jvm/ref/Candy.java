package com.jvm.ref;

/**
 * @author: @zyz
 */
public class Candy {
	public static void main (String[] args) {
		Candy candy=new Candy ();
		candy.f (2);
	}

	public void f(int x)
	{
		Runnable runnable=new Runnable () {
			@Override
			public void run () {
//				x=3;
				System.out.println (x);
			}
		};

		new Thread (runnable).start ();

	}

}

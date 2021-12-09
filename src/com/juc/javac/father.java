package com.juc.javac;

/**
 * @author: @zyz
 */
public abstract class father {

	public father () {
	}

	public   void f1()
	{
		f2();
	}
	abstract void  f2();

	public static void f3()
	{

	}
}

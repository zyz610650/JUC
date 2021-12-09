package com.juc.javac;

/**
 * @author: @zyz
 */
public class son extends father1{
	public  String name="son";
	@Override
	public  void f1()
	{
		System.out.println ("erzi");
	}
	@Override
	public  void  f2()
	{
		StringBuffer stringBuffer=new StringBuffer ();
		System.out.println ("I am f2");
		Double d=new Double (2);



	}

	public static void f3()
	{

	}

	public static void main (String[] args) {
		father1 son=new son ();
		son.f1();
		System.out.println (son.name);;


	}
}

package com.juc.thread;

/**
 * @author: @zyz
 */
public class IntegerDemo1 {
	public static void main (String[] args) {
		Integer i=new Integer (129);
		Integer a=123;
		System.out.println (i==a);
		Integer i4 = 127;//编译时被翻译成：Integer i4 = Integer.valueOf(127);
		Integer i5 = 127;
		System.out.println(i4 == i5);

		System.out.println(i4 == i);
		i=150;
		System.out.println(i4 == i);

		Double d=new Double (21.2);

		d=23.2;
		System.out.println (d);
	}
}

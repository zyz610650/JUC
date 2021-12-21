package com.jvm.ref;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: @zyz
 */
public class Load4 {
	public static void main(String[] args)
	{
		String str="a";
		Integer a=Integer.valueOf (2);
		ServiceLoader<Parent> allImpls = ServiceLoader.load(Parent.class);
		Iterator <Parent> iter = allImpls.iterator();
		while(iter.hasNext()) { iter.next(); }
//		System.out.println(E.a);
//		System.out.println(E.b);
//		System.out.println(E.c);
	}
}
class E {
	public static final int a = 10;
	public static final String b = "hello";
	public static final Integer c = 200;
	static {

		System.out.println ("初始化");
	}
}
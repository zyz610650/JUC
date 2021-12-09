package com.juc.juc;

/**
 * @author: @zyz
 */
public class finallyTest {
	public static void main(String[] args) { int result = test(); System.out.println(result); }

	public static int test() { try {
		int i=1/2;
		return 10; }finally {
		System.out.println ("SAds");
		return 1;
	}
	 }
}

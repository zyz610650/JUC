package com.juc.juc;

/**
 * @author: @zyz
 */
public class finallyTest1 {
	public static void main(String[] args) { int result = test(); System.out.println(result); }

	public static int test() {
		int i=1/0;
		return 10;
	}
}

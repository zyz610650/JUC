package com.juc.thread;

/**
 * @author: @zyz
 */
public class finalDemo {
	public static void main (String[] args) {
		User user=new User ();
		user.age=4;
		System.out.println (user.age);
	}
}

final class User{

	int age=2;
}
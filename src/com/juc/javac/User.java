package com.juc.javac;

/**
 * @author: @zyz
 */
public abstract class User {
	static final String str = null;
	private String name="zyz";

	public User (String name) {
		this.name = name;
	}
	private void f(){

	}
	public User () {

	}

	public static void main (String[] args) {
		User user=new User () {
			@Override
			public int hashCode () {
				return super.hashCode ();
			}

			@Override
			public boolean equals (Object obj) {
				return super.equals (obj);
			}

			@Override
			protected Object clone () throws CloneNotSupportedException {
				return super.clone ();
			}

			@Override
			public String toString () {
				return super.toString ();
			}

			@Override
			protected void finalize () throws Throwable {
				super.finalize ();
			}
		};
		System.out.println (user.name);
	}
}

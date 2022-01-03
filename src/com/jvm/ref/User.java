package com.jvm.ref;

import java.lang.reflect.Field;

/**
 * @author: @zyz
 */
public class User {
	public int id;

	public static void main (String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
		Class clazz=User.class;
		User user=new User ();
		Object instance = clazz.newInstance ();
		Field f=clazz.getField ("id");
		f.setAccessible (true);
		String fild="2";
		Object o=fild;
		f.set (user,o);
		f.getType ();
		System.out.println (f.getType ());
		System.out.println (user.id);
	}
}

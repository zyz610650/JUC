package com.jvm.ref;

/**
 * @author: @zyz
 */
public class SubClass implements SuperClass<String>{
	@Override
	public String method(String param) {
		return param;
	}

	public static void main (String[] args) {
		SuperClass subClass=new SubClass ();
		subClass.method (new Object ());
	}
}

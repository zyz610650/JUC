package com.juc.juc;

import com.juc.po.Account;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义AtomicInteger
 * @author: @zyz
 */
public class MyAtomicInteger {
	private volatile int value;
	private static final Unsafe unsafe;
	private static final long offest;
		static {
			try {


				Field theUnsafe = Unsafe.class.getDeclaredField ("theUnsafe");
				theUnsafe.setAccessible (true);
				unsafe = (Unsafe) theUnsafe.get ("");
//				Unsafe.getUnsafe () 不行
				Field id_field= MyAtomicInteger.class.getDeclaredField ("value");
				offest = unsafe.objectFieldOffset (id_field);
			} catch ( IllegalAccessException | NoSuchFieldException e) {
			     throw new RuntimeException (e);
			}
		}

		public MyAtomicInteger(int value)
		{
			this.value=value;
		}

		public void decrease(int data)
		{
			int oldValue=value;
			while (true)
			{
				if(unsafe.compareAndSwapInt (this,offest,oldValue,oldValue-data))
					return;
			}
		}

		public int getValue()
		{
			return value;
		}

	public static void main (String[] args) {
		MyAtomicInteger myAtomicInteger=new MyAtomicInteger (5);
		myAtomicInteger.decrease (3);
		System.out.println (myAtomicInteger.getValue ());
	}

}

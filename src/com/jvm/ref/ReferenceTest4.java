package com.jvm.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: @zyz
 */
public class ReferenceTest4 {
	private static final int _4MB = 4 * 1024 * 1024;

	public static void main(String[] args) {
		//  list --> WeakReference --> byte[]
		List< WeakReference <byte[]> > list = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			WeakReference<byte[]> ref = new WeakReference<>(new byte[_4MB]);
			list.add(ref);
			for (WeakReference<byte[]> w : list) {
				System.out.print(w.get()+" ");
			}
			System.out.println();

		}
		System.gc ();
		for (WeakReference<byte[]> w : list) {
			System.out.print(w.get()+" ");
		}
		System.out.println("循环结束：" + list.size());
	}

}

package com.jvm.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: @zyz
 */
public class ReferenceTest5 {
	private static final int _4MB = 4 * 1024 * 1024;

	public static void main(String[] args) {


		// 引用队列
		ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

		SoftReference <byte[]> ref = new SoftReference<>(new byte[_4MB], queue);
		SoftReference <byte[]> ref1 = new SoftReference<>(new byte[_4MB], queue);
		SoftReference <byte[]> ref2 = new SoftReference<>(new byte[_4MB], queue);


		// 从队列中获取无用的 软引用对象，并移除
		Reference<? extends byte[]> poll = queue.poll();
		while( poll != null) {
			System.out.println ("A");
			poll = queue.poll();
		}



	}

}

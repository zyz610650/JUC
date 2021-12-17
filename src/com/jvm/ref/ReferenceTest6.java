package com.jvm.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;

/**
 * @author: @zyz
 */
public class ReferenceTest6 {
	private static final int _4MB = 4 * 1024 * 1024;

	public static void main(String[] args) throws InterruptedException {

//		-Xmx10m -XX:+DisableExplicitGC
		// 引用队列
		ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

		WeakReference <byte[]> ref = new WeakReference<>(new byte[_4MB], queue);
		WeakReference <byte[]> ref1 = new WeakReference<>(new byte[_4MB], queue);
		WeakReference <byte[]> ref2 = new WeakReference<>(new byte[_4MB], queue);

		System.gc ();



		// 从队列中获取无用的 软引用对象，并移除
		Reference<? extends byte[]> poll = queue.poll();
		while( poll != null) {
			System.out.println ("回收");
			poll = queue.poll();
		}
		System.out.println ("等待GC");
	Thread.sleep (2000);

			poll = queue.poll();
			while( poll != null) {
				System.out.println ("回收");
			poll = queue.poll();
			}
		ByteBuffer

	}

}

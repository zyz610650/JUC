package com.jvm.ref;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: @zyz
 */
public class ReferenceTest2 {
	public static void main (String[] args) throws InterruptedException {


		ReferenceQueue<byte[]> queue=new ReferenceQueue <> ();


		WeakReference<byte[]> weakReference=new WeakReference <> (new byte[1024],queue);

		System.gc ();

		Reference < ? extends byte[] > poll = queue.poll ();


		if (poll!=null) System.out.println (poll+"5455555555sadsadsa");

	}
}

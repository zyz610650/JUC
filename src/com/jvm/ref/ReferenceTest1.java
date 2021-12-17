package com.jvm.ref;

import com.jvm.StringDemo;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: @zyz
 */
public class ReferenceTest1 {
	public static void main (String[] args) {



		List<WeakReference<byte[]>> list=new ArrayList <> ();
		ReferenceQueue<byte[]> queue=new ReferenceQueue <> ();
		for (int i=0;i<5;i++)
		{
			WeakReference<byte[]> ref=new WeakReference <> (new byte[1024],queue);
			System.out.println (ref.get ());
			list.add (ref);
			System.out.println (list.size ());
		}
		System.gc ();
		Reference <? extends byte[]> poll=queue.poll ();
		while (poll !=null)
		{
			System.out.println ("a");
			list.remove (poll);
			poll=queue.poll ();
		}
		System.out.println ("===============");
		for (WeakReference<byte[]> reference:list)
		{
			System.out.println (reference.get ());
		}

	}
}

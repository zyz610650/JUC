package com.jvm.ref;

import com.jvm.StringDemo;
import com.sun.corba.se.spi.ior.ObjectKey;

import javax.jws.Oneway;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.sql.Ref;

/**
 * @author: @zyz
 */
public class ReferenceTest {
	public static void main (String[] args) {
//		Object obj=new Object ();
////		System.out.println (obj);
////		obj=null;
////		System.gc ();
////		System.out.println (obj);
//		ReferenceQueue queue=new ReferenceQueue ();
//		WeakReference  weakReference=new WeakReference <> (obj,queue);
//		obj=null;
//
//		System.out.println (weakReference.get ());
//
//		System.gc ();
//		Reference  ref;
//		while ((ref= (Reference ) queue.poll ())!=null)
//		{
//			System.out.println ("1");
//		}
//     	System.out.println (weakReference.get ());
//		System.out.println (weakReference);


		ReferenceQueue queue = new ReferenceQueue();
		WeakReference ref=new WeakReference(new StringDemo () ,queue);

		ref = null;
		System.gc ();
		while ((ref = (WeakReference) queue.poll()) != null) {
         // 清除ref
			System.out.println ("A");

		}

	}
}

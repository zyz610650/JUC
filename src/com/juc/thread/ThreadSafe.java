package com.juc.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author: @zyz
 */
@Slf4j
public class ThreadSafe {

	public static void main (String[] args) {
		ThreadSafeSubClass test=new ThreadSafeSubClass ();
		for(int i=0;i<1;i++)
		{
			new Thread (()->{
				test.method1 ();
			},"main"+i).start ();
		}
	}
	public final void method1()  {
		ArrayList<String> list=new ArrayList ();
	for(int i=0;i<100;i++)
	{

		method2 (list);
		method3 (list);
	}

//	log.debug (String.valueOf (list.hashCode ()));
//		System.out.println (list);
//		System.out.println ();
	}


	public void method2(ArrayList<String> list)
	{
//		log.debug ("方法2  ");
//		log.debug (String.valueOf ("方法2  "+list));
		list.add("1");

	}

	public void method3(ArrayList<String> list)
	{

		list.add("1");

	}

}
@Slf4j
class ThreadSafeSubClass extends ThreadSafe{
	@Override
	public void method3 (ArrayList < String > list) {
		new Thread (()->{

//			log.debug ("方法3  ");
			list.remove (0);
////			System.out.println (list.hashCode ());
//			list.add ("2");
		},"sub").start ();
	}
}

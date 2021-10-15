package com.juc.patern;

import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.security.GuardedObject;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: @zyz
 *
 * Test24 的实现逻辑是
 * 一个大信箱 MailBox
 * 信箱里有小盒子存放每个线程的信件GuardedObjectWithId(表示大信箱里的小盒子，一个小盒子存放一个线程的信件)
 * GuardedObjectWithId存放到MailBox的map
 *  GuardedObjectWithId里的response存放的信件和id标明是哪个用户
 *
 *  我的这个实现逻辑是省略了GuardedObjectWithId 还是它那个好
 */
@Slf4j
public class GuardedSuspensionPattern {

	public static void main (String[] args) throws InterruptedException {
		Guarded guarded=new Guarded ();
		// 客户
		for(int i=0;i<5;i++)
		{
			int id= Guarded.getId ();
			new Thread (()->{
				log.debug ("Customer-[{}] 等待接收信件",String.valueOf (id) );
				guarded.createObject (id);
				guarded.get (50000,id);
				log.debug ("Customer-[{}] 收到信件",String.valueOf (id) );
			},"Customer-"+i).start ();

		}
		Thread.sleep (1000);
		// postman
		for (Integer id:guarded.getIds ()) {
			new Thread (()->{
				log.debug ("Postman-[{}] 送了一封信",String.valueOf (id) );
				guarded.put (id);
			},"postman-"+id).start ();
		}
	}

}

class Guarded{


	private static final Map<Integer,GuardedObjects> map=new ConcurrentHashMap<>();
	private static final AtomicInteger id=new AtomicInteger ();

	public GuardedObjects get (long timeout , int id)
	{
		synchronized (this)
		{
			long begin=System.currentTimeMillis ();
			long passedTime=0;
			long waitTime=timeout-passedTime;
			while(waitTime>0)
			{
				GuardedObjects value=map.get (id);
				if(value.isValid) {
					this.remove (id);
					return value;
				}else
				{
					try {
						this.wait (waitTime);
					} catch (InterruptedException e) {
						e.printStackTrace ();
					}
				}
				passedTime=System.currentTimeMillis()-begin;
				waitTime=timeout-passedTime;
			}
		}
		return null;
	}

	public  void remove(int id)
	{
		map.remove (id);
	}
	public void put (int id)
	{
		synchronized (this)
		{
			map.put (id,new GuardedObjects (true));
			this.notifyAll (); //必须放在synchronized里面
		}


	}

	public static int getId()
	{
		return id.getAndIncrement ();
	}
	public  Set<Integer> getIds()
	{
		return map.keySet ();
	}
	public void createObject(int id)
	{
		map.put (id,new GuardedObjects (false));
	}


}
class GuardedObjects
{
	public GuardedObjects (boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValid;
}


class Customer{
	int id;

	public Customer (int id) {
		this.id = id;
	}
}

class PostMan{
	int id;
	public PostMan (int id) {
		this.id = id;
	}
}

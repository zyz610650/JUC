package com.juc.threadpool.pool;



import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: @zyz
 */
public class BlockingQueue<T>{
	private ReentrantLock lock=new ReentrantLock ();
	private Condition fullWaitSet=lock.newCondition ();
	private Condition emptyWaitSet=lock.newCondition ();
	private Queue<T> queue=new ArrayDeque <> ();

	private int CAPACITY=36;

	public BlockingQueue () {
	}

	public BlockingQueue (int capacity) {
		this.CAPACITY = capacity;
	}

	/**
	 * 超时阻塞获取
	 * @param timeout
	 * @param timeUnit
	 * @return
	 */
	public T poll(long timeout, TimeUnit timeUnit)
	{
		lock.lock ();
		try{
			long nanos=timeUnit.toNanos (timeout);
			while (queue.isEmpty ())
			{

				if (nanos<=0) return null;
				nanos= emptyWaitSet.awaitNanos (nanos);
			}
			T task =queue.poll ();
			fullWaitSet.signal ();
			return task;
		} catch (InterruptedException e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();

		}
		return null;

	}

	/**
	 * 阻塞获取
	 * @return
	 */
	public T take()
	{
		lock.lock ();
		try{
			while (queue.isEmpty ())
			{
				try {
					emptyWaitSet.await ();
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
			T task=queue.poll ();
			fullWaitSet.signal ();
			return task;
		}finally {
			lock.unlock ();
		}
	}

	/**
	 * 阻塞添加
	 * @param task
	 */
	public void put(T task)
	{
		lock.lock ();
		try{
			while (queue.size ()==CAPACITY)
			{
				try {
					fullWaitSet.await ();
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
			queue.add (task);
			emptyWaitSet.signal ();
		}finally {
			lock.unlock ();
		}
	}

	/**
	 * 带超时时间阻塞添加
	 * @param task
	 * @param timeout
	 * @param timeUnit
	 * @return
	 */
	public boolean offer(T task,long timeout,TimeUnit timeUnit)
	{
		lock.lock ();
		try {
			long nanos= timeUnit.toNanos (timeout);
			while (queue.size ()==CAPACITY)
			{
				try {
					if (nanos<=0) return false;
					nanos=fullWaitSet.awaitNanos (nanos);
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
			queue.add (task);
			emptyWaitSet.signal ();
			return true;
		}finally {
			lock.unlock ();
		}
	}

	/**
	 * 队列大小
	 * @return
	 */
	public int size()
	{
		lock.lock ();
		try{
			return queue.size ();
		}finally {
			lock.unlock ();
		}
	}

	/**
	 * 带拒绝策略的放置任务
	 * @param rejectPolicy
	 * @param task
	 */
	public void tryPut(RejectPolicy<T> rejectPolicy,T task)
	{
		lock.lock ();
		try{
			if (queue.size ()==CAPACITY)
			{
				rejectPolicy.reject (this,task);
				return;
			}
			queue.add (task);
			emptyWaitSet.signal ();
		}finally {
			lock.unlock ();
		}
	}


}

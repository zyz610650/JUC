package com.juc.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: @zyz
 */
@Slf4j
public class BlockingQueue<T> {
	private final Deque <T> queue=new ArrayDeque <> ();

	private int capacity;

	private final ReentrantLock lock=new ReentrantLock ();

	private Condition emptyWaitSet=lock.newCondition ();

	private Condition fullWaitSet=lock.newCondition ();

	public BlockingQueue(int capacity)
	{
		this.capacity=capacity;
	}

	/**
	 * 阻塞获取
	 * @return
	 */
	public T take()
	{
		lock.lock ();
		try {
			while(queue.isEmpty ())
			{
			  emptyWaitSet.await ();
			}
			T t=queue.pollFirst ();
			fullWaitSet.signal();
			return t;
		} catch (Exception e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();
		}
		return  null;
	}


	/**
	 * 超时阻塞
	 * @param timeout
	 * @param timeUnit
	 * @return
	 */
	public T poll(long timeout, TimeUnit timeUnit)
	{
		timeout=timeUnit.toNanos (timeout);
		lock.lock ();
		try {
			if (queue.size ()==0)
			{
				log.debug("等待获取任务 ...");
				emptyWaitSet.awaitNanos (timeout);
			}
			T obj=null;
			if (queue.size ()>0)
			{
				capacity--;
				log.debug("获取到任务,当前队列大小为: {}...",capacity);
				obj=queue.pollFirst ();
				emptyWaitSet.signal ();

			}else{
				log.debug("等待超时,没有获得任务 ...");
			}
			return obj;
		} catch (InterruptedException e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();
		}
		return null;
	}


	/**
	 * 阻塞添加
	 * @param task
	 */
	public void put(T task)
	{
		lock.lock ();
		try {
			while (queue.size ()== capacity)
			{
				log.debug("等待加入任务队列 {} ...", task);
				fullWaitSet.await ();
			}
			log.debug("加入任务队列 {},当前队列大小：{}", task,++capacity);
			queue.addLast (task);
			emptyWaitSet.signal ();
		} catch (InterruptedException e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();
		}
	}

	/**
	 * 超时添加
	 * @param task
	 * @param timeout
	 * @param timeUnit
	 * @return
	 */
	public boolean offer(T task,long timeout,TimeUnit timeUnit)
	{
		lock.lock ();
		try {
			timeout=timeUnit.toNanos (timeout);
			if (queue.size ()==capacity)
			{
				fullWaitSet.awaitNanos (timeout);
			}
				if (queue.size ()<capacity)
				{
					log.debug("加入任务队列 {},当前队列大小：{}", task,++capacity);
					queue.addLast (task);
					emptyWaitSet.signal ();
					return true;
				}else {
					log.debug("加入任务失败 {},当前队列大小：{}", task,capacity);
					return false;
				}
		} catch (InterruptedException e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();
		}
		return  false;
	}


	public void tryPut(RejectPolicy<T> rejectPolicy,T task)
	{
		lock.lock ();
		try {
			rejectPolicy.reject (this,task);
		} catch (Exception e) {
			e.printStackTrace ();
		} finally {
			lock.unlock ();
		}

	}


}

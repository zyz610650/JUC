package com.juc.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * @author: @zyz
 */
@Slf4j
public class ThreadPool {

	/**
	 * 任务队列
	 */
	private BlockingQueue<Runnable> taskQueue;

	/**
	 * 线程集合
	 */
	private HashSet < Worker > workers=new HashSet < Worker > ();

	private RejectPolicy<Runnable> rejectPolicyRejectPolicy;

	/**
	 * 核心线程数
	 */
	private int coreSize;

	private long timeout;

	private TimeUnit timeUnit;

	public class Worker extends Thread {
		private Runnable task;
		public Worker(Runnable task)
		{
			this.task=task;
		}

		@Override
		public void run () {
			while (task!=null||(task=taskQueue.poll (timeout,timeUnit))!=null)
			{
				log.debug("正在执行...{}", task);
				task.run ();
				log.debug("{}任务执行完毕", task);
				task=null;
			}
			synchronized (workers)
			{
				log.debug("worker 被移除{}", this);
				workers.remove(this);
			}
		}
	}

	public ThreadPool ( int coreSize , long timeout , int queueCapacity,TimeUnit timeUnit,RejectPolicy < Runnable > rejectPolicyRejectPolicy ) {

		this.rejectPolicyRejectPolicy = rejectPolicyRejectPolicy;
		this.coreSize = coreSize;
		this.timeout = timeout;
		this.timeUnit = timeUnit;
		this.taskQueue=new BlockingQueue <> (queueCapacity);
	}

	public void execute(Runnable task)
	{
		synchronized (workers)
		{
			if (workers.size ()<coreSize)
			{
				Worker worker=new Worker (task);
				log.debug("新增 worker{}, {}", worker, task);
				workers.add (worker);
				worker.run ();
			}else {
				taskQueue.tryPut (rejectPolicyRejectPolicy,task);
		}
		}
	}

}



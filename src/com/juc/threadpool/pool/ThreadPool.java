package com.juc.threadpool.pool;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

/**
 * @author: @zyz
 */
public class ThreadPool {
	private BlockingQueue<Runnable> taskQueue;
	private HashSet<Woker> wokers=new HashSet <> ();
	private TimeUnit timeUnit;
	private long timeout;
	private RejectPolicy<Runnable> rejectPolicy;
	private int coreSize;


	public ThreadPool (BlockingQueue < Runnable > taskQueue , HashSet < Woker > wokers , TimeUnit timeUnit , long timeout , RejectPolicy < Runnable > rejectPolicy , int coreSize) {

		this.taskQueue = taskQueue;
		this.wokers = wokers;
		this.timeUnit = timeUnit;
		this.timeout = timeout;
		this.rejectPolicy = rejectPolicy;
		this.coreSize = coreSize;
	}

	public void execute(Runnable task)
	{
		synchronized (wokers)
		{
			if (wokers.size ()<coreSize)
			{
				Woker woker=new Woker (task);
				wokers.add (woker);
				woker.start ();
			}else {
				taskQueue.put (task);
			}
		}
	}

	class Woker extends Thread{
		private Runnable task;

		public Woker () {
		}

		public Woker (Runnable task) {
			this.task = task;
		}

		@Override
		public void run () {
			while (task!=null||(task=taskQueue.take ())!=null)
			{
				try {
					task.run ();
				} finally {
					task=null;
				}
			}
			synchronized (wokers)
			{
				wokers.remove (this);
			}
		}
	}
}



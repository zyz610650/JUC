package com.juc.threadpool.custom;

import com.juc.threadpool.custom.ThreadPool;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: @zyz
 */
@Slf4j
public class ThreadPoolTest {
	public static void main (String[] args) {
		ThreadPool threadPool=new ThreadPool (5,1, 10,TimeUnit.SECONDS,((queue , task) -> {
			queue.offer (task,2,TimeUnit.SECONDS);
		}));

		for(int i=0;i<10;i++)
		{
			int j=i;
			threadPool.execute (()->{
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				log.debug("task-{} 执行完毕", j);
			});
		}
	}
}

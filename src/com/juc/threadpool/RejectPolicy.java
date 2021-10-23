package com.juc.threadpool;

/**
 * @author: @zyz
 */
public interface RejectPolicy <T>{

		public void reject(BlockingQueue queue,T task);

}

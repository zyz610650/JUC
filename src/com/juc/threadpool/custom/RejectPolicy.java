package com.juc.threadpool.custom;

import com.juc.threadpool.custom.BlockingQueue;

/**
 * @author: @zyz
 */
public interface RejectPolicy <T>{

		public void reject(BlockingQueue queue, T task);

}

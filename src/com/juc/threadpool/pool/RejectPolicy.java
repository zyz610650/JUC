package com.juc.threadpool.pool;

import java.util.Queue;

/**
 * @author: @zyz
 */
public interface RejectPolicy<T> {

	void reject(BlockingQueue<T> queue,T task);
}

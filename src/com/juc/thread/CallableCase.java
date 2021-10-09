package com.juc.thread;

import java.util.concurrent.*;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class CallableCase {

    static class Task implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);
            return 2;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        Task task =new Task();
        Future<Integer> res=executorService.submit(task);
        System.out.println(res.get());
    }
}

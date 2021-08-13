package com.thread;

import java.util.concurrent.*;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class FutureTaskCase {

    static class Task implements Callable<Integer>
    {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);
            return 2;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask=new FutureTask<>(new Task());
        executorService.submit(futureTask);
        System.out.println(futureTask.get());
    }
}

package com.juc.Concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class CountDownLatchDemo {

    static class PreTaskThread implements Runnable
    {

        private String task;
        private CountDownLatch countDownLatch;
        private PreTaskThread(String task,CountDownLatch countDownLatch)
        {
            this.task=task;
            this.countDownLatch=countDownLatch;
        }
        @Override
        public void run() {
            Random random=new Random();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(task+" - 任务完成");
                countDownLatch.countDown();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch =new CountDownLatch(3);
        new Thread(()->{
            System.out.println("等待数据加载....");
            System.out.println(String.format("还有%d个前置任务",countDownLatch.getCount()));
            try {
                countDownLatch.await();

                System.out.println("over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(new PreTaskThread("加载地图数据",countDownLatch)).start();
        Thread.sleep(1000);

        new Thread(new PreTaskThread("加载人物数据",countDownLatch)).start();

        new Thread(new PreTaskThread("加载背景音乐",countDownLatch)).start();
    }
}

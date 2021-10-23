package com.juc.conncurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class CyclicBarrierDemo {

    static class PreTaskThread implements Runnable
    {

        private String task;
        private CyclicBarrier cyclicBarrier;

        private PreTaskThread(String task, CyclicBarrier cyclicBarrier)
        {
            this.task=task;
            this.cyclicBarrier=cyclicBarrier;
        }
        @Override
        public void run() {

            for (int i=0;i<4;i++)
            {
                Random random=new Random();
                try {
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(String.format("关卡%d的任务%s完成",i,task));
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                cyclicBarrier.reset();
            }

        }
    }

    public static void main(String[] args) {

        Thread t=new Thread() {
            @Override
            public void run() {

            }
        };

        CyclicBarrier cyclicBarrier=new CyclicBarrier(3,()->{
            System.out.println("本关卡所有前置任务完成，开始游戏....");
        });
        new Thread(new PreTaskThread("加载地图数据",cyclicBarrier)).start();


        new Thread(new PreTaskThread("加载人物数据",cyclicBarrier)).start();

        new Thread(new PreTaskThread("加载背景音乐",cyclicBarrier)).start();
    }
}

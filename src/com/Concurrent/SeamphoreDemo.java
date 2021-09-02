package com.Concurrent;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class SeamphoreDemo {

    static class MyThread implements Runnable{
        private int value;
        private Semaphore semaphore;

        public MyThread(int value,Semaphore semaphore)
        {
            this.value=value;
            this.semaphore=semaphore;
        }
        @Override
        public void run() {

            try {

                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println(String.format("当前线程是%d,还剩%d个资源,还有%d个线程在等待",value,semaphore.availablePermits(),semaphore.getQueueLength()));

                Random random=new Random();
                Thread.sleep(random.nextInt(1000));
                semaphore.release();
                System.out.println(String.format("线程%d释放了资源",value));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Semaphore seamphore=new Semaphore(3);
            for (int i=0;i<10;i++)
            {

                new Thread(new MyThread(i,seamphore)).start();
            }
        }
    }
}

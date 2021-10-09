package com.juc.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: @zyz
 */
@Slf4j
public class ThreadState {

    public static void main(String[] args) {
        Object LOCK=new Object();
        Thread thread1=new Thread();// NEW
        Thread thread2=new  Thread(()->{ //RUNNABLE-运行
            while (true)
            {

            }
        });
        Thread thread3=new Thread(()->{ //Runnable-> 可运行状态
            Thread.yield();
        });

        Thread thread4=new Thread(()->{ //Runnable-> 阻塞
            // 阻塞状态
        });

        Thread thread5=new Thread(()->{
           synchronized (LOCK)
           {

           }
        });

        Thread thread6=new Thread(()->{
            try {
                thread5.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread7=new Thread(()->{
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        synchronized (LOCK)
        {
            thread1.start();
            thread2.start();
            thread3.start();
            thread4.start();
            thread5.start();
            thread6.start();
            thread7.start();
            log.debug(String.valueOf(thread1.getState()));
            log.debug(String.valueOf(thread2.getState()));
            log.debug(String.valueOf(thread3.getState()));
            log.debug(String.valueOf(thread4.getState()));
            log.debug(String.valueOf(thread5.getState()));
            log.debug(String.valueOf(thread6.getState()));
            log.debug(String.valueOf(thread7.getState()));
        }
    }
}

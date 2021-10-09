package com.juc.thread;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class RunnableCase {

    public static class MyThread implements java.lang.Runnable {
        @Override
        public void run()
        {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        new Thread(myThread,"Thread1").start();
        new Thread(myThread,"Thread2").start();

        new Thread(()->{
            System.out.println("MyThread");
        }).start();
    }

}

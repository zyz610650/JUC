package com.thread;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class ThreadCase {

    public static class MyThread extends java.lang.Thread{
        @Override
        public void run()
        {
            System.out.println("MyThread");
        }
    }

    public static void main(String[] args) {
        MyThread myThread=new MyThread();
        myThread.start();

        new java.lang.Thread(()->{
            System.out.println("MyThread");
        }).start();
    }

}

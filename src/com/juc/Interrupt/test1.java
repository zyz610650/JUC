package com.juc.Interrupt;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class test1 {

    static class MyThread implements Runnable{
        Object o=new Object();
        @Override
        public void run() {

            synchronized (o)
            {
               while (true)
               {
                   try {
                       Thread.sleep(100);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println(Thread.currentThread().getName());
               }
            }

        }


    }
    public static void main(String[] args) throws InterruptedException {


        MyThread myThread=new MyThread();
        Thread thread=new Thread(myThread);
        thread.start();
        Thread.sleep(100);
        new Thread(myThread).start();
        thread.interrupt();
    }
}

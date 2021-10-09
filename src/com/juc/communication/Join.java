package com.juc.communication;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class Join {
    static class ThreadA implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("我是子线程 我先1s");
                Thread.sleep(10000);
                System.out.println("我是子线程 我睡完了1s");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(new ThreadA());
        thread.start();
        thread.join(2000);
        System.out.println("Over");


    }
}

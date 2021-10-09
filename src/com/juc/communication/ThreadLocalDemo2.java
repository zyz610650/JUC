package com.juc.communication;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class ThreadLocalDemo2 {

    static ThreadLocal<Integer> threadLocal=new ThreadLocal<>();

    static class ThreadA implements Runnable{
        private ThreadLocal<Integer> threadLocal;

        public ThreadA(ThreadLocal<Integer> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA输出 "+threadLocal.get());
        }
    }
    static class ThreadB implements Runnable
    {
        private ThreadLocal<Integer> threadLocal;

        public ThreadB(ThreadLocal<Integer> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {

            //threadLocal.set(new User(2));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadB输出 "+threadLocal.get());
        }

        }


    public static void main(String[] args) {
        threadLocal.set(2);

        new Thread(new ThreadA(threadLocal)).start();
        new Thread(new ThreadB(threadLocal)).start();
    }
}

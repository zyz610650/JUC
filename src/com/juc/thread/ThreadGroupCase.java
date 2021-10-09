package com.juc.thread;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class ThreadGroupCase {
    public static void main(String[] args) {
        Thread thread=new Thread(()->{
            System.out.println("线程组名字:"+Thread.currentThread().getThreadGroup().getName());
            System.out.println("线程名字"+Thread.currentThread().getName());
        });
        thread.start();
        System.out.println("执行main方法的线程名字 "+Thread.currentThread().getName());




        ThreadGroup threadGroup=new ThreadGroup("group1");
        Thread[] threads=new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
    }
}

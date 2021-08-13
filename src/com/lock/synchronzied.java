package com.lock;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class synchronzied {

    private  static  Object lock=new Object();

    static class ThreadA implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                for (int i=0;i<100;i++)
                {
                    System.out.println("Thread A"+i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                    System.out.println("A over");
                //到这 该方法还没执行完 锁还没释放
                lock.notify();
                //到这执行到最后一个括号，该方法才执行完毕
            }

        }
    }
    static class ThreadB implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                for (int i=0;i<100;i++)
                {
                    System.out.println("Thread B"+i);
                    lock.notify();
                    try {
                        lock.wait();
                        //i==99时 释放锁 下面的还没有执行 该方法还没执行完毕，等到上面的A线程释放完锁会接着把该方法执行完
                        if (i==99)
                            System.out.println("B over");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
       new Thread(new ThreadA()).start();
        Thread.sleep(10);
       new Thread(new ThreadB()).start();

    }

}

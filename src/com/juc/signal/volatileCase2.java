package com.juc.signal;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address: https://www.cnblogs.com/dolphin0520/p/3920373.html
 * @idea:
 */
//   加volatile 关键字
public class volatileCase2 {
    private static volatile int signal=0;

    static class ThreadA implements Runnable{
//   这种
//        thread: Thread-0  1
//        thread: Thread-0  2
//        thread: Thread-1  2
//        thread: Thread-1  3
//        thread: Thread-0  4
//        thread: Thread-1  5
//        thread: Thread-0  5
//        thread: Thread-1  6
//        thread: Thread-0  7
//        thread: Thread-1  8
//        thread: Thread-0  8
//        thread: Thread-1  9
        @Override
        public void run() {
            while (signal<100)
            {

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    signal++;
                System.out.println("thread: "+Thread.currentThread().getName()+"  "+signal);

            }
        }
    }

    static class ThreadB implements Runnable{


        @Override
        public void run() {
            while (signal<100)
            {

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                signal++;
                System.out.println("thread: "+Thread.currentThread().getName()+"  "+signal);

            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(10);
        new Thread(new ThreadA()).start();
    }
}

package com.juc.signal;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address: https://www.cnblogs.com/dolphin0520/p/3920373.html
 * @idea:
 */
// 不加volatile 关键字
public class volatileCase1 {
    private static  int signal=0;

    static class ThreadA implements Runnable{
// 出现这种情况可能是 线程0     signal++;从缓存得到的signal为1 +1后为2 写到内存中
        //   在执行这条语句的时候还需要去内存读取 signal，但是在执行这条语句之前线程1 执行了 signal++ 从内存得到的是2， 2+1=3
        //   这时线程1 接着执行 从内存中读到的是3
        //   System.out.println("thread: "+Thread.currentThread().getName()+"  "+signal); 就把2给跳过了
//        thread: Thread-0  1
//        thread: Thread-0  3
//        thread: Thread-1  3
//        thread: Thread-0  4
//        thread: Thread-1  4
//        thread: Thread-1  6
//        thread: Thread-0  6

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

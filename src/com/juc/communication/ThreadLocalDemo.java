package com.juc.communication;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class ThreadLocalDemo {
    static class User{
        public User(int num) {
            this.num = num;
        }

        int num;

        @Override
        public String toString() {

            return    Thread.currentThread().getName()+" User{" +
                    "num=" + num +
                    '}';
        }
    }
    static class ThreadA implements Runnable{
        private ThreadLocal<User> threadLocal;

        public ThreadA(ThreadLocal<User> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            User user=new User(1);
            threadLocal.set(user);
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
        private ThreadLocal<User> threadLocal;

        public ThreadB(ThreadLocal<User> threadLocal) {
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
        ThreadLocal<User> threadLocal=new ThreadLocal<>();
        User user=new User(0);
        threadLocal.set(user);

        new Thread(new ThreadA(threadLocal)).start();
        new Thread(new ThreadB(threadLocal)).start();
    }
}

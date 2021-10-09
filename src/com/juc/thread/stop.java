package com.juc.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 两阶段终止模式
 * @author: @zyz
 */
@Slf4j
public class stop {
    public static void main(String[] args) throws InterruptedException {

        MyThread thread=new MyThread();
        thread.start();

        Thread.currentThread().join(6);
        thread.interrupt();
    }

     static class MyThread extends Thread {
        @Override
        public void run() {
            while (true)
            {
                if (Thread.currentThread().isInterrupted())
                {
                    log.debug("处理后事");
                    break;
                }else{
                    log.debug("没有被打断");
                    try {
                        Thread.sleep(2);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}

package com.juc.thread;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 *
 * 烧水泡茶案例
 * @author: @zyz
 */
@Slf4j
public class case1 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1=new Thread(()->{
            try {
                Thread.sleep(1000);
                log.debug("洗水壶");
                Thread.sleep(5000);
                log.debug("烧开水");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"小马");

        Thread thread2=new Thread(()->{
            try {
                Thread.sleep(1000);
                log.debug("洗茶壶");
                Thread.sleep(1000);
                log.debug("洗茶杯");
                Thread.sleep(2000);
                log.debug("洗茶叶");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"小张");

        thread1.start();
        thread2.start();
        thread1.join();
        log.debug("烧完开水");
        thread2.join();
        log.debug("茶叶茶杯准备完毕");



    }
}

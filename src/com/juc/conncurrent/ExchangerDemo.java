package com.juc.conncurrent;

import java.util.concurrent.Exchanger;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class ExchangerDemo {
    public static void main(String[] args) {
        Exchanger<String> exchanger=new Exchanger<>();
        new Thread(()->{
            try {
                System.out.println("这是线程A,得到了另一个线程的数据:"+exchanger.exchange("这是来自线程A的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                System.out.println("这是线程B,得到了另一个线程的数据:"+exchanger.exchange("这是来自线程B的数据"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

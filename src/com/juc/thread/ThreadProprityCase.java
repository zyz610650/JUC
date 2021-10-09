package com.juc.thread;

import java.util.stream.IntStream;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class ThreadProprityCase {
    public static void main(String[] args) {
        IntStream.range(1,10).forEach(i->{
            Thread thread= new Thread(()-> System.out.println(String.format("当前线程为 %s 当前线程优先级为 %d",Thread.currentThread().getName(),Thread.currentThread().getPriority()))
        );
        thread.setPriority(i);
        thread.start();
        });

    }
}

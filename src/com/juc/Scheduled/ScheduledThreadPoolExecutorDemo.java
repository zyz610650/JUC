package com.juc.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class ScheduledThreadPoolExecutorDemo {

    private static final ScheduledExecutorService executor= new ScheduledThreadPoolExecutor(1, Executors.defaultThreadFactory());
    private static SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        executor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                if (haveMsgAtCurrentTime())
                {
                    System.out.println(df.format(new Date()));
                    System.out.println("大家注意了,我要发消息了!");
                }
            }
        },1,1, TimeUnit.SECONDS);
    }
    static boolean haveMsgAtCurrentTime()
    {
        return true;
    }
}

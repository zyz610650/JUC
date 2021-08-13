package com.thread;

import javax.management.RuntimeErrorException;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class ThreadException {
    public static void main(String[] args) {
        ThreadGroup threadGroup=new ThreadGroup("group1")
        {
            @Override
            public void uncaughtException(Thread t,Throwable e)
            {
                System.out.println("出现异常   "+t.getName()+": "+e.getMessage());
            }
        };

        Thread thread=new Thread(threadGroup,()-> {
            System.out.println("");
            throw new RuntimeException("异常测试");
        });
        thread.start();
    }
}

package com.juc.Concurrent;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class PhaserDemo {

    static class PreTaskThread implements Runnable {

        private String task;
        private Phaser phaser;

        String str="加载新手教程";
        private PreTaskThread(String task, Phaser phaser) {
            this.task = task;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            for (int i=1;i<4;i++)
            {
                if (i>=2&&str.equals(task)) continue;
                Random random=new Random();
                try {
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(String.format("关卡%d,需要加载%d个模块,当前模块【%s】",i,phaser.getRegisteredParties(),task));
                    if (i==1&&str.equals(task))
                    {
                        System.out.println("下从关卡一处移除加载【新手教程】模块");
                        phaser.arriveAndDeregister();
                    }else{
                        phaser.arriveAndAwaitAdvance();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Phaser phaser=new Phaser(4){
          protected  boolean onAdvance(int phase,int registeredParties)
          {
              System.out.println(String.format("第%d次关卡准备完成\n",phase+1));
              return registeredParties==0;
          }
        };
        new Thread(new PreTaskThread("加载地图数据",phaser)).start();
        new Thread(new PreTaskThread("加载人物数据",phaser)).start();
        new Thread(new PreTaskThread("加载背景音乐",phaser)).start();
        new Thread(new PreTaskThread("加载新手教程",phaser)).start();
    }
}

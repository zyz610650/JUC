package com.signal;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class VolatileTest {
    private static volatile int COUNTER = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int threadValue = COUNTER;
            while ( threadValue < 5){
          // System.out.println("ChangeListener: " +threadValue+" "+COUNTER);
                if( threadValue!= COUNTER){
                    System.out.println("Got Change for COUNTER : " + threadValue+" "+COUNTER + "");
                    threadValue= COUNTER;
                }
            }
        }
    }

    static class ChangeMaker extends Thread{
        @Override
        public void run() {
            int threadValue = COUNTER;
            while (COUNTER <5){
                System.out.println("Incrementing COUNTER to : " + (threadValue+1) + "");
                COUNTER = ++threadValue;
                System.out.println("Incrementing COUNTER1 to : " + (COUNTER) + "");
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}

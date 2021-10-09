package com.juc.signal;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class TestVolatitle1 {

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo td = new ThreadDemo();
        Thread threadA = new Thread(td);
        threadA.start();

        while (true) {
            //System.out.println("打开这句以后，会进入if条件中，程序可正常结束！");
            //或者是打开下面这句
            //Thread.sleep(1);
            if (td.isFlag()) {
                System.out.println("------------------");
                break;
            }
        }
    }
}

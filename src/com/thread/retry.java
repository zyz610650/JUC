package com.thread;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class retry {
    /// test这个方法有问题 我估计跟 线程的执行顺序有关
    public static void main(String[] args) throws InterruptedException {

//        breakRetry();
//        System.out.println("===========");
//        continueRetry();
       test();
        //System.out.println(i);
    }
    private static void test() throws InterruptedException {

        int i = 0;
        int t=0;
        retry:
        for (; i<10;t=+4) {
            System.out.println(Thread.currentThread().getName()+"wai: "+ i);
            for (; ; ) {
                i++;
                System.out.println(i); // 这行代码加不加 完全得到两个不同的答案 并且 retry再次进入for循环时因该是没在走for循环的条件
                //要么i<10不会不跳出来 和t+=4 不会不在增加
                if (i == 4)
                {
                    System.out.println(t);
                    System.out.println(Thread.currentThread().getName()+"'aaa'"+i);
                    Thread.sleep(100);
                    continue retry;
                }
            }
        }
    }

    private static void breakRetry() {
        int i = 0;
        retry:
        for (; ; ) {
            System.out.println("start");
            for (; ; ) {
                i++;
                if (i == 4)
                    break retry;
            }
        }
        //start 进入外层循环
        //4
        System.out.println(i);
    }


    private static void continueRetry() {
        int i = 0;
        retry:
        for(;;) {
            System.out.println("start");
            for(;;) {
                i++;
                if (i == 3)
                    break retry;
                System.out.println("end");
                if (i == 4)
                    break retry;
            }
        }
        //start 第一次进入外层循环
        //end i=1输出
        //end i=2输出
        //start 再次进入外层循环
        //end i=4输出
        //4 最后输出
        System.out.println(i);
    }

}

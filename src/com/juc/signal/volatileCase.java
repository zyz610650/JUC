package com.juc.signal;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address: https://www.cnblogs.com/dolphin0520/p/3920373.html
 * @idea:
 */
public class volatileCase {
    private static  int signal=0;

    static class ThreadA implements Runnable{
// https://blog.csdn.net/qq_39552268/article/details/119725273 出现这种问题的原因
        @Override
        public void run() {
            while (signal<10)
            {
                if (signal%2==0)
                {
                    System.out.println("threadA: "+signal);
//                   synchronized (this)
//                   {
//                       signal++;
//                   }
                signal++;
                }
            }
        }
    }

    static class ThreadB implements Runnable{

        @Override
        public void run() {
            while (signal<10)
            {
                if (signal%2==1)
                {
                    System.out.println("threadB: "+signal);
                 signal++;
//                    synchronized (this)
//                    {
//                        signal++;
//                    }

                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
       Thread.sleep(10);
        new Thread(new ThreadB()).start();
    }
}
//  res:  threadA: 0
//        threadA: 1
//        threadA: 1
//        threadA: 2
//        threadA: 2
//        threadA: 3
//        threadA: 3
//        threadA: 5
//        threadA: 5
//        threadA: 6
//        threadA: 6
//        threadA: 7
//        threadA: 7
//        threadA: 8
//        threadA: 9
//        threadA: 11
//        threadA: 11
//        threadA: 12
//        threadA: 13
//        threadA: 14
//        threadA: 14
//        threadA: 15
//        threadA: 16
//        threadA: 17
//        threadA: 18
//        threadA: 19
//        threadA: 20
//        threadA: 21
//        threadA: 22
//        threadA: 23
//        threadA: 24
//        threadA: 25
//        threadA: 26
//        threadA: 27
//        threadA: 28
//        threadA: 29
//        threadA: 30
//        threadA: 31
//        threadA: 31
//        threadA: 32
//        threadA: 33
//        threadA: 34
//        threadA: 35
//        threadA: 36
//        threadA: 37
//        threadA: 38
//        threadA: 39
//        threadA: 40
//        threadA: 40
//        threadA: 42
//        threadA: 42
//        threadA: 43
//        threadA: 44
//        threadA: 45
//        threadA: 46
//        threadA: 48
//        threadA: 48
//        threadA: 49
//        threadA: 50
//        threadA: 51
//        threadA: 52
//        threadA: 53
//        threadA: 54
//        threadA: 55
//        threadA: 55
//        threadA: 56
//        threadA: 56
//        threadA: 57
//        threadA: 57
//        threadA: 58
//        threadA: 58
//        threadA: 60
//        threadA: 60
//        threadA: 61
//        threadA: 61
//        threadA: 62
//        threadA: 62
//        threadA: 63
//        threadA: 63
//        threadA: 64
//        threadA: 64
//        threadA: 65
//        threadA: 65
//        threadA: 66
//        threadA: 66
//        threadA: 68
//        threadA: 68
//        threadA: 70
//        threadA: 70
//        threadA: 71
//        threadA: 71
//        threadA: 72
//        threadA: 73
//        threadA: 75
//        threadA: 74
//        threadA: 77
//        threadA: 77
//        threadA: 78
//        threadA: 78
//        threadA: 79
//        threadA: 79
//        threadA: 80
//        threadA: 80
//        threadA: 82
//        threadA: 81
//        threadA: 83
//        threadA: 83
//        threadA: 85
//        threadA: 84
//        threadA: 86
//        threadA: 86
//        threadA: 88
//        threadA: 87
//        threadA: 89
//        threadA: 90
//        threadA: 91
//        threadA: 92
//        threadA: 93
//        threadA: 93
//        threadA: 94
//        threadA: 95
//        threadA: 96
//        threadA: 96
//        threadA: 97
//        threadA: 97
//        threadA: 98
//        threadA: 99
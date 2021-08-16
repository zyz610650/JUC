package com.signal;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
class ThreadDemo implements Runnable {

    private boolean flag = false;

    @Override
    public void run() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {

        }
        flag = true;
        System.out.println("flag=" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}


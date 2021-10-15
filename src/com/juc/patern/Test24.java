package com.juc.patern;

import lombok.extern.slf4j.Slf4j;

import javax.naming.Name;
import java.util.*;


/**
 * 实现保护性暂停模式
 *  * Test24 的实现逻辑是
 *  * 一个大信箱 MailBox
 *  * 信箱里有小盒子存放每个线程的信件GuardedObjectWithId(表示大信箱里的小盒子，一个小盒子存放一个线程的信件)
 *  * GuardedObjectWithId存放到MailBox的map
 *  *  GuardedObjectWithId里的response存放的信件和id标明是哪个用户
 */
@Slf4j
public class Test24 {
    /**
     * 这种设计模式和join方法进行对比：
     * 1.这里可以做一些其他事，等待下载结果的线程不用等下载线程全部执行完之后才能去到下载结果，而是在
     * 中途通过compete方法完成了下载结果的传递。  
     * 2.并且这里使用的都是局部变量，如response等，而不用像join方法一样使用全局变量
     */
    public static void main(String[] args) {
        for (int i=0;i<15;i++){
            new People().start();
        }
        log.info("dddddddd");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<Integer> ids = Mailbox.getIds();
        System.out.println(ids);
        // 使用此方法会报错，使用注释的方法不会，原因暂时不知
//        for (Integer id : Mailbox.getIds()){
        for (int id=1;id<16;id++){
            log.info("{}",id);
            new Postman(id, "内容" +id ).start();
        }

    }
    
}

@Slf4j(topic = "people")
class People extends Thread{
    @Override
    public void run() {
        GuardedObjectWithId guardedObject = Mailbox.createGuardedObject();
        log.debug("准备收信 id:{}", guardedObject.getId());
        Object mail=guardedObject.get(100000);
        log.debug("收到信 id:{}, 内容:{}", guardedObject.getId(), mail);
    }
}

@Slf4j(topic = "postman")
class Postman extends Thread{
    private int id;
    private String mail;

    public Postman(int id, String mail) {
        this.id=id;
        System.out.println("构造方法传入的参数" + mail);
        this.mail=mail;  
    }
    
    @Override
    public void run() {
        GuardedObjectWithId guardedObjectWithId = Mailbox.getGuardedObjectWithId(id);
        log.debug("送信 id:{}, 内容:{}", id, mail);
        guardedObjectWithId.complete(mail);
    }
}


class GuardedObjectWithId {
    private Object response;
    private int id;

    public int getId() {
        return id;
    }

    public GuardedObjectWithId(int id) {
        this.id = id;
    }

    public Object get(long timeout) {
        synchronized (this) {
            // 条件不满足则等待
            long begin = System.currentTimeMillis();
            long passTime = 0;
            while (response == null) {
                //减去每次虚假唤醒所花费的时间，啥叫作虚假唤醒，就是不满足while打断的条件，但是wait却被唤醒了
                long total = timeout - passTime;  
                //超时跳出循环
                if(total<=0){
                    break;
                }
                try {
                    this.wait(total);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //如果被虚假唤醒了，那么就要计算它花费的时间
                passTime = System.currentTimeMillis() -begin;
            }
            return response;
        }
    }

    public void complete(Object response) {
        synchronized (this) {
            // 条件满足，通知等待线程
            this.response = response;
            this.notifyAll();
        }
    }
}

/*
中间解耦类
 */
@Slf4j
class Mailbox{
    // HashMap是线程安全的类
    private static Map<Integer,GuardedObjectWithId> boxes = new HashMap<>();
    
    private static int id = 1;
    
    
    // 自增操作不是原子性的
    public static synchronized int generateId(){
        return id++;
    }

    // HashMap是线程安全的类,所以不用synchronized??
    public static synchronized GuardedObjectWithId getGuardedObjectWithId(int id){
        return boxes.remove(id);
    }

    // HashMap是线程安全的类,所以不用synchronized??如果这里不加synchronized,运行会报错
    public static synchronized GuardedObjectWithId createGuardedObject(){
        int i = generateId();
        GuardedObjectWithId guardedObjectWithId = new GuardedObjectWithId(i);
        boxes.put(guardedObjectWithId.getId(),guardedObjectWithId);
        log.info(boxes.toString());
        return guardedObjectWithId;
    }
    
    public static Set<Integer> getIds(){
        return boxes.keySet();
    }
}

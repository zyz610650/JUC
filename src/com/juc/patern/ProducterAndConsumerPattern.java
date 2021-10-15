package com.juc.patern;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author: @zyz
 */
@Slf4j
public class ProducterAndConsumerPattern {

	public static void main (String[] args) throws InterruptedException {
		MessageQueue messageQueue=new MessageQueue (5);
		for (int i=0;i<20;i++)
		{
			new Thread (()->{
				messageQueue.take ();
			}).start ();
		}

		for (int i=0;i<30;i++)
		{
			new Thread (()->{
				messageQueue.put (new Message ());
			}).start ();
		}
	}
}
@Slf4j
class MessageQueue{

	private LinkedList<Message> list=new LinkedList <> ();
	private int capacity;


	public MessageQueue (int capacity) {
		this.capacity = capacity;
	}

	public Message take()
	{
		synchronized (list)
		{
			while (list.isEmpty ())
			{
				try {
					log.debug ("无数据可以消费");
					list.wait ();
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
			log.debug ("已消费数据");
			Message message=list.removeFirst ();
			list.notifyAll ();
			return message;
		}
	}

	public void put(Message message)
	{
		synchronized (list)
		{
			while (list.size ()==capacity)
			{
				try {
					log.debug ("队列已满,生产者线程[{}]等待",Thread.currentThread ().getName ());
					list.wait ();
				} catch (InterruptedException e) {
					e.printStackTrace ();
				}
			}
			list.addLast (message);
			log.debug ("已经生产消息");
			list.notifyAll ();
		}
	}

}
class Message{


}

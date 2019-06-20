package com.rabbitmq.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Consumer extends Thread {

	private CountDownLatch countDownLatch = new CountDownLatch(1);
	final static String queueName = "message_queue";
	private volatile boolean shutdown = false;

	public void receiveMsg(String message)
	{
		System.out.println("Received: " + message);
		countDownLatch.countDown();
	}
	
	public CountDownLatch getCountDownLatch()
	{
		return countDownLatch;
	}
	
	public void run() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		Consumer consumer = new Consumer();
		int i = 0;
		while(!shutdown) {
			try {
				consumer.getCountDownLatch().await(200000, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					consumer.shutdown = true;
					ctx.close();
				}
			}
			);
		}
	}
}

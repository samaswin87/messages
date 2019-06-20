package com.rabbitmq.example;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Producer extends Thread {

	final static String queueName = "message_queue";
	private static volatile boolean shutdown = false;
	private int count = 0;
	
	public void run() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		RabbitTemplate rabbitTemplate = (RabbitTemplate) ctx.getBean("rabbitTemplate");
		while(!shutdown) {
			rabbitTemplate.convertAndSend(queueName, "Message: " + count);
			count++;
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					shutdown = true;
					ctx.close();
				}
			}
			);
		}
	}

	public int getCount() {
		return count;
	}
	
}

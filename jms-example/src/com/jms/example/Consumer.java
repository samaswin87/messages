package com.jms.example;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
	
	private BlockingQueue<String> queue;
	
	Consumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		String msg;
		try {
			while((msg = queue.take()) != "Finished"){
				Thread.sleep(10);
				System.out.println("Message Received: "+ msg);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

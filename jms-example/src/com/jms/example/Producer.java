package com.jms.example;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
	
	private BlockingQueue<String> queue;
	
	Producer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run(){
		try {
			for(int i = 0; i < 50; i++) {
				System.out.println("Message Added: "+ i);
				queue.put(""+i);
				Thread.sleep(i);
			}
			queue.put("Finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

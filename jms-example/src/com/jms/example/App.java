package com.jms.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
	
	public static void main(String[] args) {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}

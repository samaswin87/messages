package com.rabbitmq.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	Producer producer = new Producer();
//        Thread tproducer = new Thread(producer);
//        tproducer.start();
        
		Consumer consumer = new Consumer();
		Thread tconsumer = new Thread(consumer);
	    tconsumer.start();
    }
}

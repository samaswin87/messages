package com.jms.queue;

import java.util.Properties;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class Sender {
	public static void main(String[] args) throws Exception {
		
		Properties jndiProps = new Properties();
		jndiProps.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		jndiProps.put("java.naming.factory.url.pkgs", "org.jboss.naming:org.jnp.interfaces");
		jndiProps.put("java.naming.provider.url", "localhost:1099");
		// get the initial context
		InitialContext context = new InitialContext(jndiProps);

		// lookup the queue object
		Queue queue = (Queue) context.lookup("jndi-name-of-queue");

		// lookup the queue connection factory
		QueueConnectionFactory conFactory = (QueueConnectionFactory) context.lookup("queue/connectionFactory");

		// create a queue connection
		QueueConnection queConn = conFactory.createQueueConnection();

		// create a queue session
		QueueSession queSession = queConn.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

		// create a queue sender
		QueueSender queSender = queSession.createSender(queue);
		queSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

		// create a simple message to say "Hello World"
		TextMessage message = queSession.createTextMessage("Hello World");

		// send the message
		queSender.send(message);

		// print what we did
		System.out.println("Message Sent: " + message.getText());

		// close the queue connection
		queConn.close();
	}
}
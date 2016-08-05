package com.redhat.demo.ErrorProcessor;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.main.Main;

/**
 * A Camel Application
 */
public class MainApp {

	/**
	 * A main() so we can easily run these routing rules in our IDE
	 */
	public static void main(String... args) throws Exception {
		CamelContext context = new DefaultCamelContext();

//		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616?username=admin&amp;password=admin");

		ActiveMQComponent amqpart = ActiveMQComponent.activeMQComponent("tcp://localhost:61616");
		amqpart.setUserName("admin");
		amqpart.setPassword("admin");

		context.addComponent("test-jms",amqpart);

		context.addRoutes(new MyRouteBuilder());
		context.start();
	}

}

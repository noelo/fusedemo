package com.redhat.demo.test;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelContextXmlTest extends CamelSpringTestSupport {

	// TODO Create test message bodies that work for the route(s) being tested
	// Expected message bodies
	protected Object[] expectedBodies = { "<something id='1'>expectedBody1</something>",
			"<something id='2'>expectedBody2</something>" };
	// Templates to send to input endpoints
	@Produce(uri = "file:{{sys:filelocn:/tmp/}}?noop=true&antInclude=*.csv")
	protected ProducerTemplate inputEndpoint;

	@Test
	public void testCamelRoute() throws Exception {

		// Define some expectations

		// For now, let's just wait for some messages// TODO Add some expectations here
		// Send some messages to input endpoints
		for (Object expectedBody : expectedBodies) {
			inputEndpoint.sendBody(expectedBody);
		}

		// Validate our expectations
		assertMockEndpointsSatisfied();
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

}

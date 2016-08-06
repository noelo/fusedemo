package com.redhat.demo.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CamelContextXmlTest2 extends CamelSpringTestSupport {

	// TODO Create test message bodies that work for the route(s) being tested
	// Expected message bodies
	protected Object[] expectedBodies = { "<something id='1'>expectedBody1</something>",
			"<something id='2'>expectedBody2</something>" };
	// Templates to send to input endpoints
	@Produce(uri = "file:{{sys:filelocn:/tmp/}}?noop=true&antInclude=*.csv")
	protected ProducerTemplate inputEndpoint;
	@Produce(uri = "activemq:queue:{{sys:localtestq:prodqueue}}?transacted=true&username=admin&password=admin")
	protected ProducerTemplate input2Endpoint;
	// Mock endpoints used to consume messages from the output endpoints and then perform assertions
	@EndpointInject(uri = "mock:output")
	protected MockEndpoint outputEndpoint;
	@EndpointInject(uri = "mock:output2")
	protected MockEndpoint output2Endpoint;
	@EndpointInject(uri = "mock:output3")
	protected MockEndpoint output3Endpoint;
	@EndpointInject(uri = "mock:output4")
	protected MockEndpoint output4Endpoint;
	@EndpointInject(uri = "mock:output5")
	protected MockEndpoint output5Endpoint;
	@EndpointInject(uri = "mock:output6")
	protected MockEndpoint output6Endpoint;

	@Test
	public void testCamelRoute() throws Exception {
		// Create routes from the output endpoints to our mock endpoints so we can assert expectations
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("activemq:queue:{{sys:localtestq:prodqueue}}?username=admin&password=admin").to(outputEndpoint);
				from("cxfrs:bean:SMSRestService?throwExceptionOnFailure=false").to(output3Endpoint);
				from("direct:name").to(output4Endpoint);
				from("ref:csvtojson").to(output2Endpoint);
				from("activemq:queue:{{sys:localtestq:prodqueue}}FailSystem?transacted=true&username=admin&password=admin&jmsMessageType=Object")
						.to(output5Endpoint);
				from("activemq:queue:{{sys:localtestq:prodqueue}}FailProcessing?transacted=true&username=admin&password=admin&jmsMessageType=Object")
						.to(output6Endpoint);
			}
		});

		// Define some expectations

		// TODO Ensure expectations make sense for the route(s) we're testing
		outputEndpoint.expectedBodiesReceivedInAnyOrder(expectedBodies);

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

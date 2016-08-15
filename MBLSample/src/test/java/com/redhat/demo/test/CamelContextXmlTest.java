package com.redhat.demo.test;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.redhat.demo.model.MessagePayload;

public class CamelContextXmlTest extends CamelSpringTestSupport {
	@Produce(uri = "direct:ProcessingRoute")
	protected ProducerTemplate input2Endpoint;

	@Test
	public void testCamelRouteGet() throws Exception {
		Exchange exchange = template.send("cxfrs://bean://SMSRestServiceClient?throwExceptionOnFailure",
				new Processor() {
					public void process(Exchange exchange) throws Exception {
						exchange.setPattern(ExchangePattern.InOut);
						Message inMessage = exchange.getIn();
						inMessage.setHeader(CxfConstants.CAMEL_CXF_RS_USING_HTTP_API, Boolean.TRUE);
						inMessage.setHeader(Exchange.HTTP_METHOD, "GET");
						inMessage.setHeader(Exchange.HTTP_PATH, "/messages");
						inMessage.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
						inMessage.setHeader(Exchange.CONTENT_TYPE, "application/json");
					}
				});

		assertEquals("Get a wrong response code", 403, exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE));
	}

	@Test
	public void testCamelRoutePost() throws Exception {

		final MessagePayload testpayload = new MessagePayload();
		testpayload.setMessage("this is a atest");
		testpayload.setRecipientemail("recipient@test.com");
		testpayload.setSenderemail("sender@test.com");
		testpayload.setSubject("testmessage");

		Exchange exchange = template.send("cxfrs://bean://SMSRestServiceClient?throwExceptionOnFailure",
				new Processor() {
					public void process(Exchange exchange) throws Exception {
						exchange.setPattern(ExchangePattern.InOut);
						Message inMessage = exchange.getIn();
						inMessage.setHeader(CxfConstants.CAMEL_CXF_RS_USING_HTTP_API, Boolean.TRUE);
						inMessage.setHeader(Exchange.HTTP_METHOD, "POST");
						inMessage.setHeader(Exchange.HTTP_PATH, "/messages");
						inMessage.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
						inMessage.setHeader(Exchange.CONTENT_TYPE, "application/json");
						inMessage.setBody(testpayload);
					}
				});

		assertEquals("Get a wrong response code", 200, exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE));
	}

	@Test
	public void testCamelRoutePostMissingPayload() throws Exception {
		Exchange exchange = template.send("cxfrs://bean://SMSRestServiceClient?throwExceptionOnFailure",
				new Processor() {
					public void process(Exchange exchange) throws Exception {
						exchange.setPattern(ExchangePattern.InOut);
						Message inMessage = exchange.getIn();
						inMessage.setHeader(CxfConstants.CAMEL_CXF_RS_USING_HTTP_API, Boolean.TRUE);
						inMessage.setHeader(Exchange.HTTP_METHOD, "POST");
						inMessage.setHeader(Exchange.HTTP_PATH, "/messages");
						inMessage.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
						inMessage.setHeader(Exchange.CONTENT_TYPE, "application/json");
						inMessage.setBody(null);
					}
				});

		assertEquals("Get a wrong response code", 400, exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE));
	}

	@Test
	public void testCamelRoutePostInvalidPayload() throws Exception {
		Exchange exchange = template.send("cxfrs://bean://SMSRestServiceClient?throwExceptionOnFailure",
				new Processor() {
					public void process(Exchange exchange) throws Exception {
						exchange.setPattern(ExchangePattern.InOut);
						Message inMessage = exchange.getIn();
						inMessage.setHeader(CxfConstants.CAMEL_CXF_RS_USING_HTTP_API, Boolean.TRUE);
						inMessage.setHeader(Exchange.HTTP_METHOD, "POST");
						inMessage.setHeader(Exchange.HTTP_PATH, "/messages");
						inMessage.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
						inMessage.setHeader(Exchange.CONTENT_TYPE, "application/json");
						inMessage.setBody("this is a test");
					}
				});

		assertEquals("Get a wrong response code", 400, exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE));
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

}

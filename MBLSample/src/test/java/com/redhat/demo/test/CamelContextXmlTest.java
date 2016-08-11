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
	public void testCamelRoute() throws Exception {

		final MessagePayload testpayload = new MessagePayload();
		testpayload.setMessage("this is a atest");
		testpayload.setRecipientemail("recipient@test.com");
		testpayload.setSenderemail("sender@test.com");
		testpayload.setSubject("testmessage");		

		Exchange exchange = template.send(
				"cxfrs://bean://SMSRestServiceClient",
				new Processor() {
					public void process(Exchange exchange) throws Exception {
						exchange.setPattern(ExchangePattern.InOut);
						Message inMessage = exchange.getIn();
						inMessage.setHeader(CxfConstants.CAMEL_CXF_RS_USING_HTTP_API, Boolean.FALSE);
						inMessage.setHeader(CxfConstants.OPERATION_NAME, "messagesGet");
						inMessage.setHeader(Exchange.ACCEPT_CONTENT_TYPE, "application/json");
						inMessage.setHeader(Exchange.CONTENT_TYPE, "application/json");
						inMessage.setBody(testpayload);
					}
				});

		assertEquals("Get a wrong response code", 403, exchange.getOut().getHeader(Exchange.HTTP_RESPONSE_CODE));
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}

}

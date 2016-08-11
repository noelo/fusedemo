package com.redhat.demo.converter;

import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class CustomResponseMapper implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		Response myrep = Response.ok(exchange.getIn().getBody(String.class)).build();
		exchange.getOut().setBody(myrep);
	}

}

package com.redhat.poc.impl;

import javax.ws.rs.core.Response;
import org.apache.camel.Exchange;
import com.redhat.demo.model.MessagePayload;

/**
 * Created by admin on 31/07/2016.
 */
public class SMSGateway {
	public void processIncomingMsg(Exchange exchange) throws Exception {
		exchange.getIn().setHeader("senderEmail", exchange.getIn().getBody(MessagePayload.class).getSenderemail());
		exchange.getIn().setHeader("recipientEmail",
				exchange.getIn().getBody(MessagePayload.class).getRecipientemail());
		exchange.getIn().setHeader("emailSubject",
				exchange.getIn().getBody(MessagePayload.class).getSubject());
		exchange.getIn().setBody(exchange.getIn().getBody(MessagePayload.class).getMessage(), String.class);
	}

	public Response processSuccess() {
		return Response.status(Response.Status.OK).entity(new String("SYSTEM IS UP")).build();
	}

	public Response processSystemFailure(java.lang.Exception caughtEx) {
		return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(new String(caughtEx.getMessage())).build();
	}

	public Response processValidationFailure(java.lang.Exception caughtEx) {
		return Response.status(Response.Status.BAD_REQUEST).entity(new String(caughtEx.getMessage())).build();
	}

}
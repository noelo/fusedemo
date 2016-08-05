package com.redhat.demo.ErrorProcessor;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    public void configure() {
    	from("test-jms:queue:prodqueueFailProcessing").to("log:message=\"Error message=${body}\"");
    }

}

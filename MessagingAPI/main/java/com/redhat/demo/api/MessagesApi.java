package com.redhat.demo.api;

import com.redhat.demo.model.Error;
import com.redhat.demo.model.MessageCorrelation;
import com.redhat.demo.model.MessagePayload;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.*;

@Path("/")
public interface MessagesApi  {
    @GET
    @Path("/messages")
    
    @Produces({ "application/json" })
    public Response messagesGet();
    @POST
    @Path("/messages")
    
    @Produces({ "application/json" })
    public Response messagesPost(@QueryParam("to") String to,@QueryParam("from") String from,MessagePayload msgpayload);
}


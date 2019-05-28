package com.nagappans.exceptions;

import org.jboss.resteasy.spi.UnhandledException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HandleUnhandledException implements ExceptionMapper<NullPointerException> {
    @Override
    public Response toResponse(NullPointerException e) {
        e.printStackTrace();
        return Response.status(500).entity(e.getMessage()).build();
    }
}

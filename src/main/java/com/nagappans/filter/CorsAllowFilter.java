package com.nagappans.filter;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@Priority(3000)
public class CorsAllowFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        if (containerResponseContext.getHeaders().containsKey("Access-Control-Allow-Origin")==false || containerResponseContext.getHeaders().get("Access-Control-Allow-Origin").isEmpty()) {
            containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "http://localhost:4200");
        }
        containerResponseContext.getHeaders().add(
                "Access-Control-Allow-Credentials", "true");
        containerResponseContext.getHeaders().add(
                "Access-Control-Allow-Headers",
                "origin, content-type, accept, authorization");
        containerResponseContext.getHeaders().add(
                "Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    }
}

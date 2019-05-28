package com.nagappans.filter;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;

import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

@Provider
@ValidateId
public class ValidateIdFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String requestedEmpId = containerRequestContext.getUriInfo().getPathParameters().get("id").get(0);
        try {
            int empId = Integer.parseInt(requestedEmpId);
            if (empId<0 || empId>100) {
                throw new Exception("Invalid Employeed Id");
            }
        } catch(Exception exception) {
            containerRequestContext.abortWith(new ServerResponse(exception.getMessage(),500, new Headers<>()));
        }
        System.out.println("method filter invoked");
    }
}

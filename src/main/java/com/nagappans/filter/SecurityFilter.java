package com.nagappans.filter;

import org.jboss.resteasy.core.Headers;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.ServerResponse;
import org.jboss.resteasy.util.Base64;

import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.StringTokenizer;

@Provider
//@Priority(2000)
public class SecurityFilter implements ContainerRequestFilter {
    private static final ServerResponse ACCESS_FORBIDDEN = new ServerResponse("NOBODY CAN ACCESS", 403, new Headers<>());
    private static final ServerResponse ACCESS_DENIED = new ServerResponse("Authorization header missing/invalid or Role Not valid", 401, new Headers<>());
    private static final ServerResponse INTERNAL_SERVER_ERROR = new ServerResponse("Authorization credentials not encoded properly", 500, new Headers<>());
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker)
            containerRequestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        Method method = methodInvoker.getMethod();
        if (!method.isAnnotationPresent(PermitAll.class)) {
            if (method.isAnnotationPresent(DenyAll.class)) {
                containerRequestContext.abortWith(ACCESS_FORBIDDEN);
                return;
            }
            final MultivaluedMap<String, String> headers = containerRequestContext.getHeaders();
            final List<String> authorization = headers.get(AUTHORIZATION_PROPERTY);

            if(authorization==null || authorization.isEmpty()) {
                containerRequestContext.abortWith(ACCESS_DENIED);
                return;
            }
            String credentials = null;
            try{
                credentials = new String(Base64.decode(authorization.get(0).replaceFirst("Basic ", "")));
            } catch(Exception exception) {
                containerRequestContext.abortWith(INTERNAL_SERVER_ERROR);
                return;
            }
            final StringTokenizer credentialsParser = new StringTokenizer(credentials, ":");
            String username = credentialsParser.nextToken();
            String password = credentialsParser.nextToken();
            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("!!!Authenticated Successfully!!!");
                //skip authorization check as roles not mentioned.
                if (method.getAnnotation(RolesAllowed.class)==null) {
                    return;
                }
                String[] rolesAllowed = method.getAnnotation(RolesAllowed.class).value();
                boolean isAllowed = false;
                for(String role: rolesAllowed) {
                    if (role.equals("admin")) {
                        System.out.println("!!Authorized successfully!!");
                        isAllowed = true;
                        break;
                    }
                }
                if (!isAllowed) {
                    containerRequestContext.abortWith(ACCESS_DENIED);
                    return;
                }
            } else {
                containerRequestContext.abortWith(ACCESS_DENIED);
                return;
            }
        }
    }
}

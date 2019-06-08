package com.nagappans.filter;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.annotation.Priority;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(4000)
public class CustomCorsFilter extends CorsFilter {

    public CustomCorsFilter() {
        this.getAllowedOrigins().add("http://localhost:4200");
    }

}

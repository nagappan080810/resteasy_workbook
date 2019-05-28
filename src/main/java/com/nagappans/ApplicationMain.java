package com.nagappans;

import com.nagappans.filter.SecurityFilter;
import com.nagappans.filter.ValidateIdFilter;
import com.nagappans.rest.EmployeeService;
import com.nagappans.rest.MessageRestService;
import org.jboss.resteasy.plugins.cache.server.ServerCacheFeature;
import org.jboss.resteasy.plugins.interceptors.encoding.GZIPEncodingInterceptor;
import org.jboss.resteasy.plugins.interceptors.encoding.ServerContentEncodingAnnotationFeature;
import org.jboss.resteasy.plugins.interceptors.encoding.ServerContentEncodingAnnotationFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class ApplicationMain extends Application {

    @Override
    public Set<Object> getSingletons() {
        return null;
    }

}

package com.nagappans.rest;

import com.nagappans.filter.ValidateId;
import com.nagappans.model.Employee;
import org.infinispan.notifications.cachelistener.annotation.CacheEntryInvalidated;
import org.jboss.resteasy.annotations.GZIP;
import org.jboss.resteasy.logging.Logger;
import org.jboss.resteasy.plugins.cache.server.ServerCache;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@Path("employees")
@XmlRootElement
public class EmployeeService {


    private static Map<Integer, Employee> employees = new HashMap<Integer, Employee>();
    static {
        employees.put(1, new Employee(1, "Nagappan", 9629230494l));
    }

    Logger logger = Logger.getLogger(EmployeeService.class);
    @XmlElement(name="getuserbyid")
    private String uri1 = "users/{id}";
    @XmlElement(name="update_employeemobileno")
    private String uri2 = "users/{id}/mobilenumber/{mobilenumber}";

    public String getUri1() {
        return getUri1();
    }

    public String getUri2() {
        return getUri2();
    }

    @GET
    @Path("/")
    @GZIP
    @Produces("application/xml")
    @PermitAll
    public EmployeeService getServiceInfo() {
        return new EmployeeService();
    }


    @GET
    @Path("/{id}")
    @GZIP
    @Produces({MediaType.APPLICATION_JSON})
    @ValidateId
    @RolesAllowed({"manager","admin"})
    public Response getUserById(@PathParam("id") int id, @Context Request req) {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(1000);
        logger.info(employees.get(id).getLastModifiedDate().toString());
        EntityTag etag = new EntityTag(employees.get(id).getLastModifiedDate().hashCode()+"");
        Response.ResponseBuilder resp = req.evaluatePreconditions(etag);
        if (resp!=null) {
            //response code 402 for precondition failed..
            return resp.cacheControl(cc).tag(etag).build();
        } else {
            System.out.println("Pre-conditon matched");
        }

        logger.info("generating response");
        //return employees.get(id);
        return Response.status(200).entity(employees.get(id)).cacheControl(cc).tag(etag).build();
    }

    @PATCH
    @CacheEntryInvalidated
    @Path("/{id}/mobilenumber/{mobilenumber}")
    @Produces({MediaType.APPLICATION_JSON})
    @RolesAllowed({"admin"})
    public Response updateUserById(@PathParam("id") int id, @PathParam("mobilenumber") String mobilenumber, @Context Request req) {
        CacheControl cc = new CacheControl();
        cc.setMaxAge(1000);
        cc.setMustRevalidate(true);
        EntityTag etag = new EntityTag(employees.get(id).getLastModifiedDate().hashCode()+"");
        Response.ResponseBuilder resp = req.evaluatePreconditions(etag);
        if (resp!=null) {
            return resp.tag(etag).build();
        }
        employees.get(id).setMobileNumber(Long.parseLong(mobilenumber));
        employees.get(id).updateDate();
        return Response.status(200).entity("mobile number updated successfully").cacheControl(cc).build();
    }
}
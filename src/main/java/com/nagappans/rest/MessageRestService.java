package com.nagappans.rest;
import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
public class MessageRestService {

    @GET
    @Path("/{param}")
    @PermitAll
    public Response printMessage(@PathParam("param") String name) {
        System.out.println("It is invoking rest api service");
        String result = String.format("Hello %s! It is RESTEasy API demo services", name);
        return Response.status(200).entity(result).build();
    }

}

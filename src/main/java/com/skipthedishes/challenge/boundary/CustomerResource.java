package com.skipthedishes.challenge.boundary;

import com.skipthedishes.challenge.entity.CustomerDAO;
import java.util.UUID;
import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

/**
 *
 * @author Kleber
 */
@Path("Customer")
public class CustomerResource {
    
    @EJB
    CustomerDAO customerDAO;
    
    @POST
    public String insert() {
        return "ok";
    }
    
    @POST
    @Path("auth")
    @Produces("application/json")
    public Response authenticate(@QueryParam("email") String email, @QueryParam("password") String password) {
        if(customerDAO.authenticate(email,password)) {
            String token = UUID.randomUUID().toString();
            return Response.ok().entity(token).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}

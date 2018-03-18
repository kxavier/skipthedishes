package com.skipthedishes.challenge.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Kleber
 */
@Path("Cousine")
public class CousineResource {
    
    @GET
    public String getAll(){
        return "ok";
    }

    @GET
    @Path("search/{searchText}")
    public String search(@PathParam("searchText") String searchText){
        return searchText;
    }

    @GET
    @Path("{cousineId}/stores")
    public String stores(@PathParam("cousineId") Integer cousineId ){
        return cousineId.toString();
    }
    
}

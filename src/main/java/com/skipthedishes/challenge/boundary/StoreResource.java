package com.skipthedishes.challenge.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 *
 * @author Kleber
 */
@Path("Store")
public class StoreResource {

    @GET
    public String getAll() {
        return "ok";
    }

    @GET
    @Path("search/{searchText}")
    public String search(@PathParam("searchText") String searchText) {
        return searchText;
    }

    @GET
    @Path("{storeId}")
    public String getStoreById(@PathParam("storeId") Integer storeId) {
        return storeId.toString();
    }
    
    @GET
    @Path("{storeId}/products")
    public String getStoreProducts(@PathParam("storeId") Integer storeId) {
        return storeId.toString();
    }
    
    
}

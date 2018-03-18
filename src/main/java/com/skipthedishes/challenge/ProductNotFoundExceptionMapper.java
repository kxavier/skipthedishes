package com.skipthedishes.challenge;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Kleber
 */
@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException>{

    @Override
    public Response toResponse(ProductNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(exception.getMessage())
                .build();
    }
    
}

package com.skipthedishes.challenge;

import javax.ws.rs.NotFoundException;

/**
 *
 * @author Kleber
 */
public class ProductNotFoundException extends NotFoundException{

    public ProductNotFoundException(String message) {
        super(message);
    }
}

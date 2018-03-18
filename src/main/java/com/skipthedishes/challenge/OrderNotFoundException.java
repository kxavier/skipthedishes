package com.skipthedishes.challenge;

import javax.ws.rs.NotFoundException;

/**
 *
 * @author Kleber
 */
public class OrderNotFoundException extends NotFoundException{

    public OrderNotFoundException(String message) {
        super(message);
    }
}

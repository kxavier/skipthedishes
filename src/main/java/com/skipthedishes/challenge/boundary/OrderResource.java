package com.skipthedishes.challenge.boundary;

import com.skipthedishes.challenge.OrderNotFoundException;
import com.skipthedishes.challenge.entity.Order;
import com.skipthedishes.challenge.entity.OrderDAO;
import java.text.ParseException;
import java.util.Optional;
import javax.ejb.EJB;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author Kleber
 */
@Path("Order")
public class OrderResource {
    
    @EJB
    OrderDAO orderDAO;
    
    @GET
    @Path("{orderId}")
    @Produces("application/json")
    public JsonObject getOrder(@PathParam("orderId") Integer orderId) {
        Optional<Order> optionalOrder = orderDAO.getById(orderId);
        Order order = optionalOrder.orElseThrow(() -> new OrderNotFoundException("No order with id " + orderId + " was found"));
        return order.toJson();
    }
    
    @POST
    public Response insertOrder(JsonObject jsonOrder) {
        Order order;
        try {
            order = new Order(jsonOrder);
        } catch (ParseException ex) {
            return Response.status(Status.BAD_REQUEST).build();
        }
        orderDAO.save(order);
        return Response.ok().build();
    }
    
    @GET
    @Path("customer")
    public String getAllCustomersOrders() {
        return "ok";
    }
    
}

package com.skipthedishes.challenge.boundary;

import java.text.DateFormat;
import java.text.ParseException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kleber
 */
public class OrderResourceIT {

    private Client client;
    private WebTarget target;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/skip-api/api/v1/Order");
    }

    @Test
    public void testGetOrderById() {
        Response response = target.path("1").request().get();
        assertThat(response.getStatus(), is(200));
        JsonObject order = response.readEntity(JsonObject.class);
        assertThat(order.getInt("id"), is(1));
        assertTrue(isValidJsonForOrder(order));
    }

    @Test
    public void testGetNonExistentOrderById() {
        Response response = target.path("99999").request().get();
        assertThat(response.getStatus(), is(404));
        String errorMessage = response.readEntity(String.class);
        assertTrue(errorMessage.contains("id 99999"));
    }

    @Test
    public void testInsert() {
        JsonObject order = Json.createObjectBuilder()
                .add("date", new Timestamp(new Date().getTime()).toString())
                .add("customerId", 1)
                .add("deliveryAddress", "test delivery address")
                .add("contact", "test contact")
                .add("storeId", 1)
                .add("orderItems", Json.createArrayBuilder().build())
                .add("total", 0.0)
                .add("status", "test status")
                .add("lastUpdate", new Timestamp(new Date().getTime()).toString())
                .build();

        Response response = target.request().post(Entity.json(order));
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testGetAllCustomerOrders() {
        Response response = target.path("customer").request().get();
        assertThat(response.getStatus(), is(200));
    }

    private boolean isValidJsonForOrder(JsonObject entity) {
        boolean hasDeliveryAddress = entity.containsKey("deliveryAddress");
        boolean hasContact = entity.containsKey("contact");
        boolean hasStoreId = entity.containsKey("storeId");
        boolean hasOrderItems = entity.containsKey("orderItems");
        boolean hasStatus = entity.containsKey("status");
        return hasDeliveryAddress && hasContact && hasStoreId && hasOrderItems && hasStatus;
    }

}

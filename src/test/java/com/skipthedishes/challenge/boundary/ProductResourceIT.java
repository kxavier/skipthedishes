package com.skipthedishes.challenge.boundary;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kleber
 */
public class ProductResourceIT {

    private Client client;
    private WebTarget target;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/skip-api/api/v1/Product");
    }

    @Test
    public void testGetAll() {
        Response response = target.request().get();
        assertThat(response.getStatus(), is(200));
        JsonArray products = response.readEntity(JsonArray.class);
        assertFalse("list of products is empty", products.isEmpty());
    }

    @Test
    public void testSearch() {
        Response response = target.path("search/global").request().get();
        assertThat(response.getStatus(), is(200));
        JsonArray products = response.readEntity(JsonArray.class);
        assertThat(products.size(), is(2));
    }

    @Test
    public void testGetProductById() {
        Response response = target.path("1").request().get();
        assertThat(response.getStatus(), is(200));
        JsonObject product = response.readEntity(JsonObject.class);
        assertThat(product.getInt("id"), is(1));
    }

    @Test
    public void testGetNonExistentProductById() {
        Response response = target.path("99999").request().get();
        assertThat(response.getStatus(), is(404));
        String errorMessage = response.readEntity(String.class);
        assertTrue(errorMessage.contains("id 99999"));
    }
}

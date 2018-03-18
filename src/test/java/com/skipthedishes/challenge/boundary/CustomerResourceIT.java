package com.skipthedishes.challenge.boundary;

import javax.json.Json;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Kleber
 */
public class CustomerResourceIT {
    
    private Client client;
    private WebTarget target;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/skip-api/api/v1/Customer");
    }
    
    @Test
    public void testInsert() {
        Response response = target.request().post(Entity.text("request"));
        assertThat(response.getStatus(), is(200));
    }
    
    @Test
    public void testAuthenticate() {
        Response response = target.path("auth")
                .queryParam("email", "kleber@email.com")
                .queryParam("password", "kleber")
                .request().post(Entity.json(Json.createObjectBuilder().build()));
        assertThat(response.getStatus(), is(200));
    }
    
    @Test
    public void testAuthenticateWithWrongCredentials() {
        Response response = target.path("auth")
                .queryParam("email", "kleber@email.com")
                .queryParam("password", "xpto")
                .request().post(Entity.json(Json.createObjectBuilder().build()));
        assertThat(response.getStatus(), is(403));
    }
}

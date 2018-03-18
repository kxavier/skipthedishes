package com.skipthedishes.challenge.boundary;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
public class CousineResourceIT {

    private Client client;
    private WebTarget target;

    @Before
    public void init() {
        this.client = ClientBuilder.newClient();
        this.target = client.target("http://localhost:8080/skip-api/api/v1/Cousine");
    }

    @Test
    public void testGetAll() {
        Response response = target.request().get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testSearch() {
        Response response = target.path("search/searchText").request().get();
        assertThat(response.getStatus(), is(200));
        String body = response.readEntity(String.class);
        assertThat(body, is("searchText"));
    }

    @Test
    public void testStores() {
        Response response = target.path("255/stores").request().get();
        assertThat(response.getStatus(), is(200));
        String body = response.readEntity(String.class);
        assertThat(body, is("255"));
    }

}

package com.example;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.example.MyResource;

public class MyResourceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(MyResource.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("api").request().get(String.class);

        assertEquals("Hello, Heroku!", responseMsg);
        
        /*final String responseMsg1 = target().path("api/Token").request().get(String.class);

        assertEquals("d305d55d-dee8-44ff-837f-b080a3f6a4b0", responseMsg1);*/
    }
}

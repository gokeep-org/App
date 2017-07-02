package com.app.rest;

import com.app.action.factory.TestActionFactory;
import com.app.domain.output.test.TestInfoOutput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TestRest {
    @GET
    @Path("/info")
    public TestInfoOutput test(@QueryParam("flag") boolean flag) throws Exception {
        return TestActionFactory.getTestInfoAction(flag).execute();
    }
}

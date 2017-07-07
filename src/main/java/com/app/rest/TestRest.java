package com.app.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.app.action.factory.TestActionFactory;
import com.app.domain.output.test.TestInfoOutput;

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

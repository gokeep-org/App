package com.app.rest;

import com.app.action.factory.TestActionFactory;
import com.app.domain.output.test.TestInfoOutput;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/****************************************
 * Copyright (c) xuning.
 * @Auther is xuning on 17-7-1
 ****************************************/
@Path("/test")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TestRest {
    @GET
    @Path("/hello")
    public TestInfoOutput test() throws Exception {
        return TestActionFactory.getTestInfoAction(true).execute();
    }
}

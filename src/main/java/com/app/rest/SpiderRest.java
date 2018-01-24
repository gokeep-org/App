package com.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by xuning on 2018/1/24.
 */
@Path("/spider")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class SpiderRest {
    private static final Logger logger = LoggerFactory.getLogger(SpiderRest.class);

    @POST
    @Path("/")
    public Map<String, Object> addSpiderProject(){
        return null;
    }

    @DELETE
    @Path("/")
    public Map<String, Object> deleteSpiderProject(){
        return null;
    }

    @PUT
    @Path("/")
    public Map<String, Object> updateSpiderProject(){
        return null;
    }

    @GET
    @Path("/")
    public Map<String, Object> searchSpiderProject(){
        return null;
    }

}


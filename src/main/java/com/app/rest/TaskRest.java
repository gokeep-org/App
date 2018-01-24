package com.app.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by xuning on 2018/1/24.
 */

@Path("/task")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TaskRest {
    private static final Logger logger = LoggerFactory.getLogger(TaskRest.class);

    @POST
    @Path("/")
    public Map<String, Object> addTask(){
        return null;
    }

    @DELETE
    @Path("/")
    public Map<String, Object> deleteTask(){
        return null;
    }

    @PUT
    @Path("/")
    public Map<String, Object> updateTask(){
        return null;
    }

    @GET
    @Path("/")
    public Map<String, Object> searchTask(){
        return null;
    }
}

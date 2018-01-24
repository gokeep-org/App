package com.app.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xuning on 2018/1/24.
 */
@Path("/status")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class StatusRest {
    @GET
    @Path("/server")
    public Map<String, Object> serverStatus(){
        Map<String, Object> statusResult = new HashMap<>();
        statusResult.put("status", 200);
        statusResult.put("message", "服务状态正常");
        statusResult.put("uuid", UUID.randomUUID().toString());
        return statusResult;
    }
}

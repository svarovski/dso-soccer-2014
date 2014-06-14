package com.tigra;

import com.sun.jersey.api.view.Viewable;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Path("/match")
public class MatchResource {
    private static final Logger LOG = Logger.getLogger(GuiceConfig.class.getName());

    @GET
    public Response list() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("greeting", "HI");
        LOG.info("list requested");
        return Response.ok(new Viewable("/index", model)).build();
    }
}

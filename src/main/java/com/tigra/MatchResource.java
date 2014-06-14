package com.tigra;

import com.sun.jersey.api.view.Viewable;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path(MatchResource.ROOT_PATH)
public class MatchResource {
    private static final Logger LOG = Logger.getLogger(GuiceConfig.class.getName());

    public static final String ROOT_PATH = "/match";

    private final MatchService service;

    @Inject
    public MatchResource(MatchService service) {
        this.service = service;
    }

    @GET
    public Response list() {
        LOG.info("match list requested");
        return Response.ok(new Viewable("/matches", model("matches", service.matchList()))).build();
    }

    @GET @Path("{matchName}")
    public Response match(@PathParam("matchName")String matchName) {
        LOG.log(Level.INFO, "Match requested: " + matchName);
        Map<Object, Object> model = model("matches", service.matchList());
        try {
            model.put("match", service.match(matchName));
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Unknown match: " + matchName);
        }
        return Response.ok(new Viewable("/matches", model)).build();
    }

    private Map<Object, Object> model(Object ... args) {
        if(args.length % 2 != 0) throw new RuntimeException("args size must be even");

        Map<Object, Object> model = new HashMap<Object, Object>();
        model.put("root", ROOT_PATH);
        for (int i = 0; i < args.length; i += 2) {
            model.put(args[i], args[i+1]);
        }
        return model;
    }
}

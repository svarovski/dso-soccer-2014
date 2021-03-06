package com.tigra;

import com.sun.jersey.api.view.Viewable;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path(MatchResource.ROOT_PATH)
@Singleton
public class MatchResource {
    private static final Logger LOG = Logger.getLogger(MatchResource.class.getName());

    public static final String ROOT_PATH = "/match";

    private final MatchService service;

    @Inject
    public MatchResource(MatchService service) {
        this.service = service;
    }

    @GET
    public Response list() {
        LOG.info("Match list requested");
        return Response.ok(new Viewable("/matches", model("matches", service.matchList()))).build();
    }

    @GET @Path("links") @Produces(MediaType.APPLICATION_JSON)
    public Response links() {
        LOG.info("Entity links requested");
        return Response.ok(Soccer.entityLinks, MediaType.APPLICATION_JSON).build();
    }

    @GET @Path("{matchName}")
    public Response match(@PathParam("matchName")String matchName) {
        LOG.log(Level.INFO, "Match requested: " + matchName);
        final Map<Object, Object> model = model("matches", service.matchList());
        final Soccer.Match match = service.match(matchName);
        if(match != null && match.getEnemies() != null) {
            model.put("match", match);
            model.put("resources", service.resourceList());
        }
        return Response.ok(new Viewable("/matches", model)).build();
    }

    @POST @Path("{matchName}")
    public Response matchCalc(@PathParam("matchName")String matchName, MultivaluedMap<String,String> form) {
        LOG.log(Level.INFO, "Calc requested for match: " + matchName);
        final Map<Soccer.Resource, Integer> condition = formToCondition(form);
        final Map<Object, Object> model = model("matches", service.matchList());
        final Soccer.Match match = service.match(matchName);
        if(match != null && match.getEnemies() != null) {
            model.put("match", match);
            model.put("condition", condition);
            final Solution solution = service.solve(match, condition);
            if(solution != null) {
                model.put("solution", solution);
            }
            else {
                model.put("error", "message.no_solution");
            }
        }
        else {
            model.put("error", "message.no_match");
        }
        return Response.ok(new Viewable("/matches", model)).build();
    }

    private Map<Soccer.Resource, Integer> formToCondition(MultivaluedMap<String, String> form) {
        Map<Soccer.Resource, Integer> condition = new EnumMap<Soccer.Resource, Integer>(Soccer.Resource.class);
        for(Soccer.Resource resource : Soccer.Resource.values()) {
            final String value = form.getFirst(resource.toString());
            try {
                condition.put(resource, Integer.parseInt(value));
            } catch (NumberFormatException e) {
                LOG.log(Level.SEVERE, "Cannot parse value for resource " + resource + ": " + value);
                condition.put(resource, 0);
            }
        }
        return condition;
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

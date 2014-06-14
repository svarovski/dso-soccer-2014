package com.tigra;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.api.core.ClasspathResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.sun.jersey.spi.container.servlet.ServletContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GuiceConfig extends GuiceServletContextListener {
    private static final Logger LOG = Logger.getLogger(GuiceConfig.class.getName());

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new JerseyServletModule() {
            @Override
            protected void configureServlets() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(ServletContainer.RESOURCE_CONFIG_CLASS, ClasspathResourceConfig.class.getName());
                params.put(ServletContainer.JSP_TEMPLATES_BASE_PATH, "/WEB-INF/jsp");
                params.put(ServletContainer.FEATURE_FILTER_FORWARD_ON_404, "true");
                filter("/*").through(GuiceContainer.class, params);
            }
        });
    }
}

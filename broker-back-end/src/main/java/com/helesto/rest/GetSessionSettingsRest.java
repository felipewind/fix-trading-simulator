package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.helesto.core.Trader;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestScoped
@Tag(name = "SessionSettings")
@Path("/session-settings")
public class GetSessionSettingsRest {

    private static final Logger LOG = LoggerFactory.getLogger(GetSessionSettingsRest.class.getName());

    @Inject
    Trader trader;

    @GET
    @Operation(summary = "Return quickfixj SessionSettings")   
    @Produces(MediaType.TEXT_PLAIN) 
    public String sessionSettings() {

        LOG.info("Request");

        return trader.getSessionSettings().toString();
    }
}
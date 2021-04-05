package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.helesto.core.Trader;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;

@RequestScoped
@Tag(name = "Session control")
@Path("/session")
public class SessionRest {

    private static final Logger LOG = LoggerFactory.getLogger(SessionRest.class.getName());

    @Inject
    Trader trader;

    @Path("settings")
    @GET
    @Operation(summary = "Return quickfixj SessionSettings")
    @Produces(MediaType.TEXT_PLAIN)
    public String settingsGet() {

        LOG.info("settingsGet");

        return trader.getSessionSettings().toString();
    }

    @Path("start")
    @PUT
    @Operation(summary = "Start FIX session and make logon")
    @Produces(MediaType.TEXT_PLAIN)
    public String startPut() throws ConfigError {

        LOG.info("startPut");

        trader.logon();

        return "FIX session started";
    }

    @Path("stop")
    @PUT
    @Operation(summary = "Stop FIX session and make logout")
    @Produces(MediaType.TEXT_PLAIN)
    public String stopPut() {

        LOG.info("stopPut");

        trader.stop();

        return "FIX session stoped";
    }

}

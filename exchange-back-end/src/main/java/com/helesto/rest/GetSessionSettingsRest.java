package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.helesto.core.Exchange;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@RequestScoped
@Path("/session-settings")
@Tag(name = "SessionSettings")
public class GetSessionSettingsRest {

    private static final Logger LOG = LoggerFactory.getLogger(GetSessionSettingsRest.class.getName());

    @Inject
    Exchange exchange;

    @Operation(summary = "Return quickfixj SessionSettings")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sessionSettings() {

        LOG.info("Begin requisition");

        return exchange.getSessionSettings().toString();
    }
}
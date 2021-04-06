package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.helesto.core.Trader;
import com.helesto.dto.SessionDto;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
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

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "Return session information")
    @APIResponse(responseCode = "200", description = "Read session information", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SessionDto.class)) })
    public Response sessionGet() {

        LOG.info("sessionGet");

        SessionDto sessionDto = new SessionDto();

        sessionDto.setSettings(trader.getSessionSettings().toString().split(System.lineSeparator()));

        sessionDto.setStart(trader.isInitiatorStarted());

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(sessionDto);

        LOG.debug("Session + GET - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(sessionDto).build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "Change session")
    @APIResponse(responseCode = "200", description = "Change session behavior", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SessionDto.class)) })
    public Response patch(SessionDto request) throws ConfigError {

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(request);

        LOG.debug("Session + PATCH - request: " + jsonString);

        if (request.isStart() && !trader.isInitiatorStarted()) {
            trader.logon();
        }

        if (!request.isStart() && trader.isInitiatorStarted()) {
            trader.stop();
        }

        SessionDto sessionDto = new SessionDto();

        sessionDto.setSettings(trader.getSessionSettings().toString().split(System.lineSeparator()));

        sessionDto.setStart(trader.isInitiatorStarted());

        jsonString = jsonb.toJson(sessionDto);

        LOG.debug("Session + PATCH - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(sessionDto).build();

    }

}

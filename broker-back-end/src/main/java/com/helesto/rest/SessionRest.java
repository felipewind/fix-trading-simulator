package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.helesto.dto.SessionDto;
import com.helesto.service.SessionService;

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
    SessionService sessionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "Return session information")
    @APIResponse(responseCode = "200", description = "Read session information", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SessionDto.class)) })
    public Response sessionGet() {

        LOG.info("sessionGet");

        SessionDto sessionDto = sessionService.sessionGet();

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(sessionDto);

        LOG.debug("Session + GET - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(sessionDto).build();
    }

    @Path("/start-initiator")
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "Start initiator")
    @APIResponse(responseCode = "200", description = "Initiator started", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SessionDto.class)) })
    public Response startInitiator() throws ConfigError {

        LOG.debug("Session + POST - start initiator");

        SessionDto sessionDto = sessionService.startInitiator();

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(sessionDto);

        LOG.debug("Session + POST - start initiator - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(sessionDto).build();

    }

    @Path("/stop-initiator")
    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "Stop initiator")
    @APIResponse(responseCode = "200", description = "Initiator stopped", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SessionDto.class)) })
    public Response stopInitiator() throws ConfigError {

        LOG.debug("Session + POST - stop initiator");

        SessionDto sessionDto = sessionService.stopInitiator();

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(sessionDto);

        LOG.debug("Session + POST - stop initiator - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(sessionDto).build();

    }

}

package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.helesto.dto.EventLogDto;
import com.helesto.dto.MessageDto;
import com.helesto.dto.MessageLogDto;
import com.helesto.service.EventLogService;
import com.helesto.service.MessageLogService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;

@RequestScoped
@Tag(name = "Log")
@Path("/logs")
public class LogsRest {

    private static final Logger LOG = LoggerFactory.getLogger(LogsRest.class.getName());

    @Inject
    EventLogService eventLogService;

    @Inject
    MessageLogService messageLogService;

    @Path("/event")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "Return Event Log information")
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = EventLogDto[].class)) })
    public Response eventLogGet() throws ConfigError {

        LOG.info("eventLogGet");

        EventLogDto[] eventLogDto = eventLogService.eventLogGet();

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(eventLogDto);

        LOG.debug("Session + GET - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(eventLogDto).build();
    }

    @Path("/messages-incoming")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "Return Message Log Incoming")
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto[].class)) })
    public Response messageLogIncomingGet() throws ConfigError {

        LOG.info("messageLogGet - incoming");

        MessageLogDto[] messageLogDto = messageLogService.messageLogGet(MessageLogService.INCOMING);

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(messageLogDto);

        LOG.debug("Session + GET - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(messageLogDto).build();
    }

    @Path("/messages-outgoing")
    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "Return Message Log Outgoing")
    @APIResponse(responseCode = "200", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MessageDto[].class)) })
    public Response messageLogOutgoingGet() throws ConfigError {

        LOG.info("messageLogGet - outgoing");

        MessageLogDto[] messageLogDto = messageLogService.messageLogGet(MessageLogService.OUTGOING);

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(messageLogDto);

        LOG.debug("Session + GET - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(messageLogDto).build();
    }

}

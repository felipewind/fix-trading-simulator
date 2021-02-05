package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.helesto.core.Trader;
import com.helesto.dto.NewOrderSingleRequestDto;
import com.helesto.dto.NewOrderSingleResponseDto;
import com.helesto.service.NewOrderSingleService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.SessionNotFound;

@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@RequestScoped
@Path("/new-order-single")
@Tag(name = "NewOrderSingle")
public class NewOrderSingleRest {

    private static final Logger LOG = LoggerFactory.getLogger(NewOrderSingleRest.class.getName());

    @Inject
    Trader trader;

    @Inject
    NewOrderSingleService newOrderSingleService;

    @POST
    @Operation(summary = "Send New Order Single")
    @APIResponse(responseCode = "200", description = "NewOrderSingleRest", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = NewOrderSingleResponseDto.class)) })
    public Response newOrderSingleRest(NewOrderSingleRequestDto request) throws SessionNotFound {

        Jsonb jsonb = JsonbBuilder.create();
        String jsonString = jsonb.toJson(request);

        LOG.debug("NewOrderSingleRest + POST - request: " + jsonString);

        NewOrderSingleResponseDto response = newOrderSingleService.newOrderSingle(request);

        jsonString = jsonb.toJson(response);

        LOG.debug("NewOrderSingleRest + POST - response: " + jsonString);

        return Response.status(Response.Status.OK).entity(response).build();
    }
}
package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.helesto.dto.OrderDto;
import com.helesto.service.NewOrderSingleService;
import com.helesto.service.OrderCancelRequestService;
import com.helesto.service.OrderService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.SessionNotFound;

@Path("/orders")
@Tag(name = "Orders")
@RequestScoped
public class OrdersRest {

        private static final Logger LOG = LoggerFactory.getLogger(OrdersRest.class.getName());

        @Inject
        NewOrderSingleService newOrderSingleService;

        @Inject
        OrderCancelRequestService orderCancelRequestService;

        @Inject
        OrderService orderService;

        @POST
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "Create a new order")
        @APIResponse(responseCode = "200", description = "Order created", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) })
        public Response create(OrderDto request) throws SessionNotFound {

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(request);

                LOG.debug("Orders + POST - request: " + jsonString);

                OrderDto response = newOrderSingleService.newOrderSingle(request);

                jsonString = jsonb.toJson(response);

                LOG.debug("Orders + POST - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(response).build();
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "List Orders", description = "List all Orders")
        @APIResponse(responseCode = "200", description = "Orders listed", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto[].class)) })
        public Response list() throws ConfigError {

                LOG.debug("OrdersListRest + GET - begin");

                OrderDto[] response = orderService.listOrders();

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(response);

                LOG.debug("OrdersListRest + GET - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(response).build();

        }

        @Path("{clOrdID}")
        @GET
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "Get Order by ClOrdID")
        @APIResponse(responseCode = "200", description = "Order", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) })
        public Response getByID(
                        @Parameter(description = "The order id to READ.", required = true) @PathParam("clOrdID") int clOrdID)
                        throws ConfigError {

                LOG.debug("OrderGetRest + GET BY ID - begin - clOrdID=[" + clOrdID + "]");

                OrderDto response = orderService.gerOrder(clOrdID);

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(response);

                LOG.debug("OrderGetRest + GET - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(response).build();

        }

        @Path("{clOrdID}")
        @DELETE
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "Cancel an order")
        @APIResponse(responseCode = "200", description = "Order cancelled", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) })
        public Response cancel(
                        @Parameter(description = "The order id to cancel.", required = true) @PathParam("clOrdID") int clOrdID)
                        throws SessionNotFound {

                LOG.debug("Orders + DELETE - clOrdID=[" + clOrdID + "]");

                OrderDto response = orderCancelRequestService.orderCancelRequest(clOrdID);

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(response);

                LOG.debug("Orders + DELETE - clOrdID=[" + clOrdID + "] - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(response).build();
        }

}

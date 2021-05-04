package com.helesto.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.helesto.dto.OrderDto;
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
        OrderService orderService;

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

        @Path("{orderID}")
        @GET
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "Get Order by OrderID")
        @APIResponse(responseCode = "200", description = "Order", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) })
        public Response getByID(
                        @Parameter(description = "The order id to READ.", required = true) @PathParam("orderID") int orderID)
                        throws ConfigError {

                LOG.debug("OrderGetRest + GET BY ID - begin - orderID=[" + orderID + "]");

                OrderDto response = orderService.getOrder(orderID);

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(response);

                LOG.debug("OrderGetRest + GET - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(response).build();

        }

        @Path("{orderID}")
        @DELETE
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "Cancel one order")
        @APIResponse(responseCode = "200", description = "Order", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) })
        public Response cancelByID(
                        @Parameter(description = "The order id to cancel.", required = true) @PathParam("orderID") int orderID)
                        throws SessionNotFound {

                LOG.debug("OrderGetRest + Cancel BY ID - begin - orderID=[" + orderID + "]");

                OrderDto response = orderService.cancelOrder(orderID);

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(response);

                LOG.debug("OrderGetRest + Cancel BY ID - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(response).build();

        }

        @Path("{orderID}")
        @PATCH
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "Update one order")
        @APIResponse(responseCode = "200", description = "Order", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) })
        public Response updateByID(OrderDto orderDto,
                        @Parameter(description = "The order id to update.", required = true) @PathParam("orderID") int orderID)
                        throws SessionNotFound {

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(orderDto);

                LOG.debug("OrderGetRest + Update BY ID - begin - orderID=[" + orderID + "]" + " orderDto = " + "\n"
                                + jsonString);

                OrderDto response = orderService.editOrder(orderID, orderDto.getOrdStatus(), orderDto.getCumQty());

                jsonString = jsonb.toJson(response);

                LOG.debug("OrderGetRest + Update BY ID - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(response).build();

        }

}

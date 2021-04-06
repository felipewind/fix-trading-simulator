package com.helesto.rest;

import java.util.ArrayList;
import java.util.List;

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

import com.helesto.dto.OrderDto;
import com.helesto.exceptions.BusinessError;
import com.helesto.exceptions.BusinessErrorException;
import com.helesto.service.NewOrderSingleService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.SessionNotFound;

@Path("/orders")
@Tag(name = "Orders", description = "Orders CRUD")
@RequestScoped
public class OrdersRest {

        private static final Logger LOG = LoggerFactory.getLogger(OrdersRest.class.getName());

        @Inject
        NewOrderSingleService newOrderSingleService;

        @POST
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "Create a new order")
        @APIResponse(responseCode = "200", description = "Create a new order", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto.class)) })
        public Response create(OrderDto request) throws SessionNotFound {

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(request);

                LOG.debug("Orders + POST - request: " + jsonString);

                newOrderSingleService.newOrderSingle(request);

                jsonString = jsonb.toJson(request);

                LOG.debug("Orders + POST - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(request).build();
        }

        @GET
        @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
        @Operation(summary = "List Orders", description = "List all Orders")
        @APIResponse(responseCode = "200", description = "Orders", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDto[].class)) })
        @APIResponse(responseCode = "422", description = "Business Error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessError.class)) })
        @APIResponse(responseCode = "500", description = "System error", content = {
                        @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessError.class)) })
        public Response list() throws BusinessErrorException {

                LOG.debug("OrdersListRest + GET - begin");

                List<OrderDto> listOrder = new ArrayList<>();

                OrderDto order = new OrderDto();

                order.setAccount("1");
                order.setClOrdId("2");
                order.setOrderQty(100.5);
                order.setPrice(231.87);
                order.setSymbol("BBAS3");
                order.setSide("1");

                listOrder.add(order);

                order = new OrderDto();

                order.setAccount("11");
                order.setClOrdId("12");
                order.setOrderQty(1100.5);
                order.setPrice(2231.87);
                order.setSymbol("PETR4");
                order.setSide("2");

                listOrder.add(order);

                order = new OrderDto();

                order.setAccount("21");
                order.setClOrdId("22");
                order.setOrderQty(1.5);
                order.setPrice(21.87);
                order.setSymbol("USIM5");
                order.setSide("1");

                listOrder.add(order);

                OrderDto[] response = listOrder.toArray(new OrderDto[0]);

                Jsonb jsonb = JsonbBuilder.create();
                String jsonString = jsonb.toJson(response);

                LOG.debug("OrdersListRest + GET - response: " + jsonString);

                return Response.status(Response.Status.OK).entity(response).build();

        }

}

package com.helesto.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.helesto.dto.OrderDto;
import com.helesto.dto.OrderListDto;
import com.helesto.exceptions.BusinessError;
import com.helesto.exceptions.BusinessErrorException;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/orders")
@Tag(name = "Orders", description = "Orders CRUD")
@RequestScoped
public class OrdersRest {

    private static final Logger LOG = LoggerFactory.getLogger(OrdersRest.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Operation(summary = "List Orders", description = "List all Orders")
    @APIResponse(responseCode = "200", description = "Orders", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = OrderListDto.class)) })
    @APIResponse(responseCode = "422", description = "Business Error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessError.class)) })
    @APIResponse(responseCode = "500", description = "System error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = BusinessError.class)) })
    public Response list() throws BusinessErrorException {

            LOG.debug("OrdersListRest + GET - begin");

            OrderListDto response = new OrderListDto();

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

            response.setListOrder(listOrder);

            return Response.status(Response.Status.OK).entity(response).build();

    }



    
}

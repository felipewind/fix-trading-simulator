import { Component, Inject } from "@angular/core";
import { Order } from "../model/order.model";
import { OrderRepository } from "../model/order.repository.model";

@Component({
    selector: "fwTable",
    templateUrl: "table.component.html"
})
export class TableComponent {

    constructor(public orderRepository: OrderRepository) {
    }

    getOrders(): Order[] {
        return this.orderRepository.getOrders();
    }

}
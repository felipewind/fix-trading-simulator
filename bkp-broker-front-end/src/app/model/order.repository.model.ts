import { Injectable } from "@angular/core";
import { Order } from "./order.model";
import { OrderRestDataSource } from "./order.rest.datasource";

@Injectable()
export class OrderRepository {
    private orders: Order[] = new Array<Order>();
    private locator = (p: Order, clOrdId: string) => p.clOrdId == clOrdId;

    constructor(private dataSource: OrderRestDataSource) {
        this.dataSource.getData().subscribe(data => this.orders = data);
    }

    getOrders(): Order[] {
        return this.orders;
    }

    getOrder(clOrdId: string): Order {
        return this.orders.find(p => this.locator(p, clOrdId));
    }

}
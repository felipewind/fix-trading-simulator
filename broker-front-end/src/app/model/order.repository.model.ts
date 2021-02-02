import { Injectable } from "@angular/core";
import { Order } from "./order.model";
// import { OrderRestDataSource } from "./order.rest.datasource";
import { OrderStaticDataSource } from "./order.static.datasource";

@Injectable()
export class OrderRepository {
    private orders: Order[] = new Array<Order>();
    private locator = (p: Order, clOrdId: string) => p.clOrdId == clOrdId;

    constructor(private dataSource: OrderStaticDataSource) {
        this.orders = this.dataSource.getData();
    }

    // constructor(private dataSource: OrderRestDataSource) {
    //     this.dataSource.getData().subscribe(data => this.Orders = data);
    // }

    getOrders(): Order[] {
        return this.orders;
    }

    getOrder(clOrdId: string): Order {
        return this.orders.find(p => this.locator(p, clOrdId));
    }

    // saveOrder(Order: Order) {
    //     if (Order.clOrdId == null) {
    //         this.dataSource.saveOrder(Order)
    //             .subscribe(p => this.Orders.push(p));
    //     } else {
    //         this.dataSource.updateOrder(Order).subscribe( p => {
    //             let index = this.Orders
    //                 .findIndex(item => this.locator(item, p.clOrdId));
    //             this.Orders.splice(index, 1, Order);
    //         });
    //     }
    // }

    // deleteOrder(clOrdId: string) {
    //     this.dataSource.deleteOrder(clOrdId).subscribe(() => {
    //         let index = this.Orders.findIndex(p => this.locator(p, clOrdId));
    //         if (index > -1) {
    //             this.Orders.splice(index, 1);
    //         }            
    //     })
    // }
}
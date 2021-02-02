import { Injectable } from "@angular/core";
import { Order } from "./order.model";

@Injectable()
export class OrderStaticDataSource {
    private data: Order[];

    constructor() {
        this.data = new Array<Order>(
            new Order("1", "PETR4", "1", 100, 275, "123"),
            new Order("2", "BBAS3", "1", 200, 48.95, "456"),
            new Order("3", "USIM5", "1", 100, 19.50, "111"),
            new Order("4", "FRIO3", "2", 500, 34.95, "222"),
            new Order("5", "VALE5", "2", 300, 16, "333"));
    }

    getData(): Order[] {
        return this.data;
    }
}
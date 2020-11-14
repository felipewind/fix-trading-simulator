import { Order } from "./order.model";
import { SimpleDataSource } from "./datasource.model";
export class Model {
    private dataSource: SimpleDataSource;
    private orders: Order[];
    private locator = (p: Order, id: number) => p.id == id;
    constructor() {
        this.dataSource = new SimpleDataSource();
        this.orders = new Array<Order>();
        this.dataSource.getData().forEach(p => this.orders.push(p));
    }
    getOrders(): Order[] {
        return this.orders;
    }
    getOrder(id: number): Order {
        return this.orders.find(p => this.locator(p, id));
    }
    saveOrder(Order: Order) {
        if (Order.id == 0 || Order.id == null) {
            Order.id = this.generateID();
            this.orders.push(Order);
        } else {
            let index = this.orders.findIndex(p => this.locator(p, Order.id));
            this.orders.splice(index, 1, Order);
        }
    }
    deleteOrder(id: number) {
        let index = this.orders.findIndex(p => this.locator(p, id));
        if (index > -1) {
            this.orders.splice(index, 1);
        }
    }
    private generateID(): number {
        let candidate = 100;
        while (this.getOrder(candidate) != null) {
            candidate++;
        }
        return candidate;
    }
}
import { Order } from "./order.model";
export class SimpleDataSource {
    private data: Order[];
    constructor() {
        this.data = new Array<Order>(
            new Order(1, "AAPL", "Apple", "1", "BUY", 400, 118.44, "111"),
            new Order(2, "CSCO", "Cisco Systems", "2", "SELL", 300, 41.27, "222"),
            new Order(3, "MSFT", "Microsoft", "2", "SELL", 100, 214.88, "111"),
            new Order(4, "INTC", "Intel", "1", "BUY", 500, 45.30, "555"),
            new Order(5, "NVDA", "Nvidia", "2", "SELL", 1000, 532.96, "111"));
    }
    getData(): Order[] {
        return this.data;
    }
}
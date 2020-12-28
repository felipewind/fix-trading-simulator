import { Component } from "@angular/core";
import { Model } from "./repository.model";
import { Order } from "./order.model";

@Component({
    selector: "app",
    templateUrl: "template.html"
})
export class OrderComponent {
    model: Model = new Model();
    getOrders(): Order[] {
        return this.model.getOrders();
    }
}
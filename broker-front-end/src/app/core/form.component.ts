import { Component } from "@angular/core";
import { NgForm } from "@angular/forms";
import { Order } from "../model/order.model";
import { OrderRepository } from "../model/order.repository.model"
import { NewOrderSingleService } from "../fix/fix.newOrderSingle.service";

@Component({
    selector: "fwForm",
    templateUrl: "form.component.html",
    styleUrls: ["form.component.css"]
})
export class FormComponent {
    order: Order = new Order();

    constructor(public orderRepository: OrderRepository, private newOrderSingle: NewOrderSingleService) {
    }

    submitForm(form: NgForm) {
        // if (form.valid) {
            console.log(`FormComponent - antes do newOrderSingle`);
            this.newOrderSingle.send(this.order).subscribe(data => this.order = data);
        // }
    }

    resetForm() {
        this.order = new Order();
    }

}
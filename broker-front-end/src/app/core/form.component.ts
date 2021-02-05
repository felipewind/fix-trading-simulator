import { Component } from "@angular/core";
import { NgForm } from "@angular/forms";
import { NewOrderSingle } from "../model/new-order-single/new-order-single";
import { NewOrderSingleResponse } from "../model/new-order-single/new-order-single-response";
import { NewOrderSingleService } from "../fix/fix.newOrderSingle.service";

@Component({
    selector: "fwForm",
    templateUrl: "form.component.html",
    styleUrls: ["form.component.css"]
})
export class FormComponent {
    newOrderSingle: NewOrderSingle = new NewOrderSingle();
    newOrderSingleResponse: NewOrderSingleResponse;

    constructor(private newOrderSingleService: NewOrderSingleService) {
    }

    submitForm(form: NgForm) {
        // if (form.valid) {

        console.log(`FormComponent - antes do newOrderSingle`);
        this.newOrderSingleService.send(this.newOrderSingle).subscribe(data => 
            { 
                this.newOrderSingleResponse = data; 
                console.log(`data=${JSON.stringify(data)}`);        
            });
            
        console.log(`newOrderSingleResponse=${JSON.stringify(this.newOrderSingleResponse)}`);

        // }
    }

    resetForm() {
        this.newOrderSingle = new NewOrderSingle();
    }

}
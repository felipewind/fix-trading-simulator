import { Component } from "@angular/core";
import { Model } from "./repository.model";
@Component({
    selector: "app",
    templateUrl: "template.html"
})
export class OrderComponent {
    model: Model = new Model();
}
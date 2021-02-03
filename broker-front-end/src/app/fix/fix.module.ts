import { NgModule } from "@angular/core";
import { NewOrderSingleService, REST_URL } from "./fix.newOrderSingle.service";
import { HttpClientModule, HttpClientJsonpModule } from "@angular/common/http";

@NgModule({
    imports: [HttpClientModule, HttpClientJsonpModule],
    providers: [NewOrderSingleService,
        { provide: REST_URL, useValue: `http://${location.hostname}:8080/new-order-single` }]
})
export class FixModule { }
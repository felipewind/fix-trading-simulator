import { NgModule } from "@angular/core";
import { OrderRepository } from "./order.repository.model";
import { HttpClientModule, HttpClientJsonpModule } from "@angular/common/http";
import { OrderRestDataSource, REST_URL } from "./order.rest.datasource";

@NgModule({
    imports: [HttpClientModule, HttpClientJsonpModule],
    providers: [OrderRepository, 
        OrderRestDataSource,
        { provide: REST_URL, useValue: `http://${location.hostname}:8080/orders` }]
})
export class ModelModule { }
import { NgModule } from "@angular/core";
import { OrderRepository } from "./order.repository.model";
import { HttpClientModule, HttpClientJsonpModule } from "@angular/common/http";
// import { OrderRestDataSource , REST_URL } from "./order.rest.datasource";
import { OrderStaticDataSource } from "./order.static.datasource";

@NgModule({
    imports: [HttpClientModule, HttpClientJsonpModule],
    providers: [OrderRepository, OrderStaticDataSource]
        // OrderRestDataSource] 
    // , { provide: REST_URL, useValue: `http://${location.hostname}:3500/products` }]
})
export class ModelModule { }
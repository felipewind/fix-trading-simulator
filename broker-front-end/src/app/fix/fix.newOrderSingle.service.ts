import { Injectable, Inject, InjectionToken } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, throwError } from "rxjs"; 
import { catchError } from "rxjs/operators";
import { Order } from "../model/order.model";

export const REST_URL = new InjectionToken("rest_url_new_order_single");

@Injectable()
export class NewOrderSingleService {
    constructor(private http: HttpClient,
        @Inject(REST_URL) private url: string) {}    

    send(order: Order): Observable<Order> {
        console.log(`Antes do sendRequest order=${JSON.stringify(order)}`);
        return this.sendRequest<Order>("POST", this.url, order);
    }

    private sendRequest<T>(verb: string, url: string, body?: Order): Observable<T> {
        let myHeaders = new HttpHeaders();
        myHeaders = myHeaders.set("Access-Key", "<secret>");
        myHeaders = myHeaders.set("Application-Names", ["broker-front-end", "fix-simulator"]);
        console.log(`Antes do httpRequest order=${JSON.stringify(body)}`);
        return this.http.request<T>(verb, url, { 
            body: body ,
            headers: myHeaders
        }).pipe(catchError((error: Response) => 
            throwError(`Network Error: ${error.statusText} (${error.status})`)));
    }
}
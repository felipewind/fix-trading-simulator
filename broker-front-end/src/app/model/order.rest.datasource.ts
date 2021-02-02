import { Injectable, Inject, InjectionToken } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, throwError } from "rxjs"; 
import { Order } from "./order.model";
import { catchError } from "rxjs/operators";

export const REST_URL = new InjectionToken("rest_url");

@Injectable()
export class OrderRestDataSource {
    constructor(private http: HttpClient,
        @Inject(REST_URL) private url: string) {}
    
    getData(): Observable<Order[]> {
        return this.sendRequest<Order[]>("GET", this.url);
    }

    saveOrder(Order: Order): Observable<Order> {
        return this.sendRequest<Order>("POST", this.url, Order);
    }

    updateOrder(Order: Order): Observable<Order> {
        return this.sendRequest<Order>("PUT", `${this.url}/${Order.clOrdId}`, Order);
    }

    deleteOrder(clOrdId: string): Observable<Order> {
        return this.sendRequest<Order>("DELETE", `${this.url}/${clOrdId}`);
    }

    private sendRequest<T>(verb: string, url: string, body?: Order): Observable<T> {
        let myHeaders = new HttpHeaders();
        myHeaders = myHeaders.set("Access-Key", "<secret>");
        myHeaders = myHeaders.set("Application-Names", ["broker-front-end", "fix-simulator"]);
        return this.http.request<T>(verb, url, { 
            body: body ,
            headers: myHeaders
        }).pipe(catchError((error: Response) => 
            throwError(`Network Error: ${error.statusText} (${error.status})`)));
    }
}
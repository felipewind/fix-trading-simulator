import { Injectable, Inject, InjectionToken } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable, throwError } from "rxjs"; 
import { catchError } from "rxjs/operators";
import { NewOrderSingle } from "../model/new-order-single/new-order-single";
import { NewOrderSingleResponse } from "../model/new-order-single/new-order-single-response";

export const REST_URL = new InjectionToken("rest_url_new_order_single");

@Injectable()
export class NewOrderSingleService {
    constructor(private http: HttpClient,
        @Inject(REST_URL) private url: string) {}    

    send(order: NewOrderSingle): Observable<NewOrderSingleResponse> {
        let a = new NewOrderSingleResponse();
        console.log(`Antes do sendRequest order=${JSON.stringify(order)}`);
    
        let myHeaders = new HttpHeaders();
        myHeaders = myHeaders.set("Access-Key", "<secret>");
        myHeaders = myHeaders.set("Application-Names", ["broker-front-end", "fix-simulator"]);        

        return this.http.post<NewOrderSingleResponse>(this.url, { 
            body: order ,
            headers: myHeaders
        }).pipe(catchError((error: Response) => 
            throwError(`Network Error: ${error.statusText} (${error.status})`)));

    }

}
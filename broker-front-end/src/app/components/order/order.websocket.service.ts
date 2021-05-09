import { catchError, map } from 'rxjs/operators';
import { Injectable } from "@angular/core";
import { EMPTY, Observable } from "rxjs";
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { Order } from './order.model';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
    providedIn: 'root'
})
export class OrderWebSocketService {

    myWebSocket: WebSocketSubject<Order> = webSocket('ws://localhost:8080/socket/order');

    constructor(private snackBar: MatSnackBar) { }

    public connect(): Observable<Order> {
        return this.myWebSocket.asObservable().pipe(
            map((obj) => obj),
            catchError((error) => this.errorHandler(error))
        );
    }

    public close(): void {
        this.myWebSocket.complete();
    }

    errorHandler(e: any): Observable<any> {
        this.showMessage('Something wrong happened with websocket!', true);
        return EMPTY;
    }

    showMessage(msg: string, isError: boolean = false): void {
        this.snackBar.open(msg, 'X', {
            duration: 3000,
            horizontalPosition: "right",
            verticalPosition: "top",
            panelClass: isError ? ['msg-error'] : ['msg-success']
        });
    }
    
}
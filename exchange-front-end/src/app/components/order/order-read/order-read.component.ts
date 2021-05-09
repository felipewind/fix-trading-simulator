import { Component, OnDestroy, OnInit } from '@angular/core';
import { OrderWebSocketService } from '../order.websocket.service';
import { Order } from './../order.model';
import { OrderService } from './../order.service';

@Component({
  selector: 'app-order-read',
  templateUrl: './order-read.component.html',
  styleUrls: ['./order-read.component.css']
})
export class OrderReadComponent implements OnInit, OnDestroy {

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['origClOrdID', 'sideText', 'symbol', 'ordStatusText', 'price', 'orderQty', 'cumQty', 'action'];

  orders: Order[];

  constructor(
    private orderService: OrderService,
    private orderWebSocketService: OrderWebSocketService) { }


  ngOnInit() {
    this.readOrders(false);
    this.orderWebSocketService.connect().subscribe(
      order => {
        // Create a new object of array to refresh screen
        this.orders = this.orders.slice();

        // Find the index of the received order
        let index = this.orders.findIndex(item => item.orderID === order.orderID);

        if (index === -1) {
          this.orders.push(order);
        } else {
          this.orders[index] = order;
        }
      });
  }

  ngOnDestroy(): void {
    this.orderWebSocketService.close();
  }

  readOrders(showMessage: boolean) {
    this.orderService.read().subscribe(orders => {
      if (showMessage) {
        this.orderService.showMessage("Refresh done");
      }
      this.orders = orders;
    })
  }

}

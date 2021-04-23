import { Order } from './../order.model';
import { OrderService } from './../order.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order-read',
  templateUrl: './order-read.component.html',
  styleUrls: ['./order-read.component.css']
})
export class OrderReadComponent implements OnInit {

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['clOrdId', 'sideText', 'symbol', 'ordStatusText', 'price', 'orderQty', 'cumQty'];

  orders: Order[];

  constructor(private orderService: OrderService) { }

  ngOnInit() {
    this.readOrders(false);
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

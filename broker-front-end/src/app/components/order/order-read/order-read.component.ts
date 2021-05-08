import { OrderMqttService } from './../order.mqtt.service';
import { Order } from './../order.model';
import { OrderService } from './../order.service';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { IMqttMessage } from "ngx-mqtt";

@Component({
  selector: 'app-order-read',
  templateUrl: './order-read.component.html',
  styleUrls: ['./order-read.component.css']
})
export class OrderReadComponent implements OnInit {

  events: any[];
  subscription: Subscription;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['clOrdID', 'sideText', 'symbol', 'ordStatusText', 'price', 'orderQty', 'cumQty', 'action'];

  orders: Order[];

  constructor(private orderService: OrderService, private readonly eventMqtt: OrderMqttService,) { }

  ngOnInit() {
    this.readOrders(false);
    this.subscribeToTopic();
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  readOrders(showMessage: boolean) {
    this.orderService.read().subscribe(orders => {
      if (showMessage) {
        this.orderService.showMessage("Refresh done");
      }
      this.orders = orders;
    })
  }

  private subscribeToTopic() {
    this.subscription = this.eventMqtt.topic()
      .subscribe((data: IMqttMessage) => {
        let item = JSON.parse(data.payload.toString());
        this.events.push(item);
        console.log(`item=${item}`);
      });
  }


}

import { OrderService } from './../order.service';
import { Order } from './../order.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-order-create',
  templateUrl: './order-create.component.html',
  styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {

  order: Order = {
  }

  constructor(private orderService: OrderService, private router: Router) { }

  ngOnInit(): void {
  }

  createOrder(): void {
    this.orderService.create(this.order).subscribe(() => {
      this.orderService.showMessage("Order created");
      this.router.navigate(['/orders']);
    });
  }

  back(): void {
    this.router.navigate(['/orders']);
  }

}

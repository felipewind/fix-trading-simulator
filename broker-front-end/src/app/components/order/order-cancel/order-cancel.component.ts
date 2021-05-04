import { OrderService } from './../order.service';
import { Order } from './../order.model';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-cancel',
  templateUrl: './order-cancel.component.html',
  styleUrls: ['./order-cancel.component.css']
})
export class OrderCancelComponent implements OnInit {

  order: Order = {
  }

  constructor(private orderService: OrderService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const clOrdID = +this.activatedRoute.snapshot.paramMap.get('clOrdID');
    this.orderService.readById(clOrdID).subscribe((order) => {
      this.order = order;
    });
  }

  cancelOrder(): void {
    this.orderService.cancel(this.order.clOrdID).subscribe(() => {
      this.orderService.showMessage("Order cancelation submitted");
      this.router.navigate(['/orders']);
    });
  }

  back(): void {
    this.router.navigate(['/orders']);
  }

}

import { OrderService } from './../order.service';
import { Order } from './../order.model';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-edit',
  templateUrl: './order-edit.component.html',
  styleUrls: ['./order-edit.component.css']
})
export class OrderEditComponent implements OnInit {

  order: Order = {
  }

  statusList: OrdStatus[] = [
    {value: '0', viewValue: 'New'},
    {value: '1', viewValue: 'Partially Filled'},
    {value: '2', viewValue: 'Filled'},
    {value: '6', viewValue: 'Pending Cancel'},
    {value: '4', viewValue: 'Canceled'},
    {value: '8', viewValue: 'Rejected'}
  ];  

  constructor(private orderService: OrderService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    const orderID = +this.activatedRoute.snapshot.paramMap.get('orderID');
    this.orderService.readById(orderID).subscribe((order) => {
      this.order = order;
    });
  }

  editOrder(): void {
    this.orderService.edit(this.order, this.order.orderID).subscribe(() => {
      this.orderService.showMessage("Order update submitted");
      this.router.navigate(['/orders']);
    });
  }

  back(): void {
    this.router.navigate(['/orders']);
  }

}

interface OrdStatus {
  value: string;
  viewValue: string;
}
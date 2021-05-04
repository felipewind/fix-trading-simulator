import { Router } from '@angular/router';
import { HeaderService } from '../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(private router: Router, 
    private headerService: HeaderService) {
    headerService.headerData = {
      title: 'Orders',
      icon: 'storefront',
      routeUrl: 'order'
    }
  }

  ngOnInit(): void {
  }

}

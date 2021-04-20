import { Router } from '@angular/router';
import { HeaderService } from './../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css']
})
export class LogComponent implements OnInit {

  constructor(private headerService: HeaderService, private router: Router) {
    headerService.headerData = {
      title: 'Logs',
      icon: 'history',
      routeUrl: 'logs'
    }
  }

  ngOnInit(): void {
  }

  navigateToEventLog(): void {    
    this.router.navigate(['/logs/event']);
  }

  navigateToMessageIncoming(): void {    
    this.router.navigate(['/logs/messages/incoming']);
  }

  navigateToMessageOutgoing(): void {    
    this.router.navigate(['/logs/messages/outgoing']);
  }

}

import { Router } from '@angular/router';
import { HeaderService } from './../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-log',
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css']
})
export class LogComponent implements OnInit {

  mode: string = 'Outgoing';
  component: string = 'Event';

  constructor(private headerService: HeaderService, private router: Router) {
    headerService.headerData = {
      title: 'Logs',
      icon: 'history',
      routeUrl: 'logs'
    }
  }

  ngOnInit(): void {
    this.navigateToEventLog();
  }

  navigateToEventLog(): void {
    // this.router.navigate(['/logs/event']);
  }

  eventLog(): void {
    this.component = 'Event';
  }

  messageIncoming(): void {
    this.mode = 'Incoming';   
    this.component = 'Message'; 
  }

  messageOutgoing(): void {
    this.mode = 'Outgoing';  
    this.component = 'Message';  
  }

  showComponent(component: string): boolean {
    if (component === this.component) {
      return true;
    } else {
      return false;
    }
  }

}

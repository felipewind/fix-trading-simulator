import { Router } from '@angular/router';
import { Session } from './../../components/session/session.model';
import { HeaderService } from './../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {

  constructor(private headerService: HeaderService, private router: Router) {
    headerService.headerData = {
      title: 'Session',
      icon: 'settings',
      routeUrl: 'session'
    }
  }

  ngOnInit(): void {
  }

  navigateToMessages(): void {
    this.router.navigate(['/session/messages']);
  }

  navigateToSessionControl(): void {
    this.router.navigate(['/session/control']);
  }

  navigateToSessionProperties(): void {
    this.router.navigate(['/session/properties']);
  }

}

import { Router } from '@angular/router';
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
    this.navigateToSessionControl();
  }

  navigateToSessionControl(): void {
    this.router.navigate(['/session/control']);
  }

}

import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-session-header',
  templateUrl: './session-header.component.html',
  styleUrls: ['./session-header.component.css']
})
export class SessionHeaderComponent implements OnInit {

  constructor(private router: Router) {
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

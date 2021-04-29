import { Router } from '@angular/router';
import { Session } from './../session.model';
import { SessionService } from './../session.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-session-properties',
  templateUrl: './session-properties.component.html',
  styleUrls: ['./session-properties.component.css']
})
export class SessionPropertiesComponent implements OnInit {

  displayedColumnsProperties = ['key', 'value'];
  displayedColumns = ['sessionSettingsFile'];

  session: Session;

  constructor(private sessionService: SessionService, private router: Router) { }

  ngOnInit(): void {
    this.sessionService.read().subscribe(session => {
      this.session = session;
    })    
  }

}

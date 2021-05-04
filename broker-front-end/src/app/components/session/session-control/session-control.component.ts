import { Router } from '@angular/router';
import { Session } from '../session.model';
import { SessionService } from '../session.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-session-control',
  templateUrl: './session-control.component.html',
  styleUrls: ['./session-control.component.css']
})
export class SessionControlComponent implements OnInit {

  session: Session;

  constructor(private sessionService: SessionService, private router: Router) { }

  ngOnInit() {
    this.read(false);
  }

  read(showMessage: boolean): void {
    this.sessionService.read().subscribe(session => {
      if (showMessage) {
        this.sessionService.showMessage("Refresh done");
      }
      this.session = session;
    })
  }
  startSession(): void {
    this.sessionService.startInitiator().subscribe(session => {
      this.sessionService.showMessage("Session started and logon submitted");
      this.session = session;
    });
  }

  stopSession(): void {
    this.sessionService.stopInitiator().subscribe(session => {
      this.sessionService.showMessage("Session stopped");
      this.session = session;
    });
  }

  logout(): void {
    this.sessionService.logout().subscribe(session => {
      this.sessionService.showMessage("Logout submitted");
      this.session = session;
    });
  }

}

import { Session } from './../../components/session/session.model';
import { SessionService } from './../../components/session/session.service';
import { HeaderService } from './../../components/template/header/header.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.css']
})
export class SessionComponent implements OnInit {

  session: Session = {
  }

  constructor(private headerService: HeaderService,
    private sessionService: SessionService) {
    headerService.headerData = {
      title: 'Session',
      icon: 'settings',
      routeUrl: 'session'
    }
  }

  ngOnInit(): void {
  }

  startSession(): void {
    this.sessionService.startInitiator().subscribe(() => {
      this.sessionService.showMessage("Session started");
    });
  }

  stopSession(): void {
    this.sessionService.stopInitiator().subscribe(() => {
      this.sessionService.showMessage("Session stopped");
    });
  }

}

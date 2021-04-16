import { Session } from '../session.model';
import { SessionService } from '../session.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-session-control',
  templateUrl: './session-control.component.html',
  styleUrls: ['./session-control.component.css']
})
export class SessionControlComponent implements OnInit {

  /** Columns displayed in the table. Columns IDs can be added, removed, or resessioned. */
  displayedColumns = ['sessionSettingsFile'];
  displayedColumnsProperties = ['key', 'value'];

  session: Session;

  constructor(private sessionService: SessionService) { }

  ngOnInit() {
    this.read();    
  }

  read(): void {
    this.sessionService.read().subscribe(session => {
      this.session = session;
    })
  }
  startSession(): void {
    this.sessionService.startInitiator().subscribe(session => {
      this.sessionService.showMessage("Session started");
      this.session = session;
    });
  }

  stopSession(): void {
    this.sessionService.stopInitiator().subscribe(session => {
      this.sessionService.showMessage("Session stopped");
      this.session = session;
    });
  }


}

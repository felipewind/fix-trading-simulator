import { Router } from '@angular/router';
import { LogService } from './../log.service';
import { LogEvent } from '../log-event.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-log-event',
  templateUrl: './log-event.component.html',
  styleUrls: ['./log-event.component.css']
})
export class LogEventComponent implements OnInit {

  displayedColumns = ['id', 'time', 'text'];

  logEvents: LogEvent[];

  constructor(private logService: LogService, private router: Router) { }

  ngOnInit() {
    this.readLogEvent(false);
  }

  readLogEvent(showMessage: boolean) {
    this.logService.readEvent().subscribe(logEvents => {
      if (showMessage) {
        this.logService.showMessage("Refresh done");
      }
      this.logEvents = logEvents;
    })
  }

  back(): void {
    this.router.navigate(['/logs']);
  }

}

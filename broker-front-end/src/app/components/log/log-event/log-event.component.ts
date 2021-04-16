import { LogEventService } from './../log.service';
import { LogEvent } from './../log-event.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-log-event',
  templateUrl: './log-event.component.html',
  styleUrls: ['./log-event.component.css']
})
export class LogEventComponent implements OnInit {

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'time', 'text'];

  logEvents: LogEvent[];

  constructor(private logEventService: LogEventService) { }

  ngOnInit() {
    this.logEventService.readEvent().subscribe(logEvents => {
      this.logEvents = logEvents;
    })

  }

}

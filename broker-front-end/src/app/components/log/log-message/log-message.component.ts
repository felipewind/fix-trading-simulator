import { Router } from '@angular/router';
import { LogService } from './../log.service';
import { LogMessage } from './../log-message.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-log-message',
  templateUrl: './log-message.component.html',
  styleUrls: ['./log-message.component.css']
})
export class LogMessageComponent implements OnInit {

  displayedColumns = ['id', 'time', 'text'];

  title = "Message Log Incoming";

  logMessages: LogMessage[];

  constructor(private logService: LogService, private router: Router) { }

  ngOnInit() {
    this.readMessages(false);
  }

  readMessages(showMessage: boolean) {
    this.logService.readMessagesIncoming().subscribe(messageIncoming => {
      if (showMessage) {
        this.logService.showMessage("Refresh done");
      }
      this.logMessages = messageIncoming;
    })
  }

  back(): void {
    this.router.navigate(['/logs']);
  }

}

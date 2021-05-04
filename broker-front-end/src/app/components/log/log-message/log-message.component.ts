import { LogService } from './../log.service';
import { LogMessage } from './../log-message.model';
import { Component, Input, OnInit, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-log-message',
  templateUrl: './log-message.component.html',
  styleUrls: ['./log-message.component.css']
})
export class LogMessageComponent implements OnInit {

  displayedColumns = ['id', 'time', 'text'];

  @Input() mode: string = "";

  title: string = "";

  logMessages: LogMessage[];

  constructor(private logService: LogService) {
  }

  ngOnInit() {
    this.readMessages(false);
  }

  ngOnChanges(change: SimpleChanges) {
    if (change.mode) {
      this.readMessages(false);
    }
  }

  readMessages(showMessage: boolean) {
    this.title = `Message Log ${this.mode}`;

    let url = "";

    if (this.mode === "Incoming") {
      url = "incoming";
    }
    else {
      url = "outgoing";
    }

    this.logService.readMessages(url).subscribe(logMessages => {
      if (showMessage) {
        this.logService.showMessage("Refresh done");
      }
      this.logMessages = logMessages;
    })
  }

}

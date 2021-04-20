import { ActivatedRoute, Router } from '@angular/router';
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

  mode: string = "";

  title: string = "";

  logMessages: LogMessage[];

  constructor(private logService: LogService, private router: Router, private activatedRoute: ActivatedRoute) {
    if (activatedRoute.snapshot.params["mode"] === 'incoming') {
      this.mode = "Incoming";
    } else {
      this.mode = "Outgoing";
    }
    this.title = `Message Log ${this.mode}`;
  }

  ngOnInit() {
    this.readMessages(false);
  }

  readMessages(showMessage: boolean) {
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

  back(): void {
    this.router.navigate(['/logs']);
  }

}

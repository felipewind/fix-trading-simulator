import { SessionService } from '../session.service';
import { Router } from '@angular/router';
import { Message } from '../message.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-messages',
  templateUrl: './session-messages.component.html',
  styleUrls: ['./session-messages.component.css']
})
export class SessionMessagesComponent implements OnInit {

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['msgseqnum', 'message'];

  messages: Message[];

  constructor(private sessionService: SessionService, private router: Router) { }

  ngOnInit() {
    this.readMessages(false);
  }

  readMessages(showMessage: boolean) {
    this.sessionService.readMessages().subscribe(messages => {
      if (showMessage) {
        this.sessionService.showMessage("Refresh done");
      }
      this.messages = messages;
    })
  }

}

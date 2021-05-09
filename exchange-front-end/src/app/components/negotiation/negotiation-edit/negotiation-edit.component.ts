import { Component, OnInit } from '@angular/core';
import { Negotiation } from './negotiation.model';
import { NegotiationService } from './negotiation.service';

@Component({
  selector: 'app-negotiation-edit',
  templateUrl: './negotiation-edit.component.html',
  styleUrls: ['./negotiation-edit.component.css']
})
export class NegotiationEditComponent implements OnInit {

  negotiation: Negotiation;

  checked: boolean;

  buttonDescription: string;

  negotiationDescription: string;

  constructor(private negotiationService: NegotiationService) { }

  ngOnInit(): void {
    this.negotiationService.read().subscribe(negotiation => {
      this.negotiation = negotiation;
      this.checked = this.negotiation.automaticTrade;
    })
  }

  changeAutomaticNegotiation(): void {
    this.negotiation.automaticTrade = !this.negotiation.automaticTrade;
    this.checked = this.negotiation.automaticTrade;
    this.negotiationService.edit(this.negotiation).subscribe(negotiation => {
      this.negotiation = negotiation;
      if (this.negotiation.automaticTrade === true) {
        this.negotiationService.showMessage('Automatic Negotiation Activated to new orders');
      } else {
        this.negotiationService.showMessage('Automatic Negotiation Deactivated to new orders');
      }
    })
  }

}

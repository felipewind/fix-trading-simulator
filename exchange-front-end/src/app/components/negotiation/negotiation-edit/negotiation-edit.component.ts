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

  checked: boolean = true;

  constructor(private negotiationService: NegotiationService) { }

  ngOnInit(): void {
    this.negotiationService.read().subscribe(negotiation => {
      this.negotiation = negotiation;
      this.checked = negotiation.automaticTrade;
    })    
  }

  click(): void {
    console.log(`click checked=${this.checked}`);
  }

}

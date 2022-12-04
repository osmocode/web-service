import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Wallet } from "src/app/models/wallet";


@Component({
  selector: 'app-wallet-card',
  templateUrl: './wallet-card.component.html',
  styleUrls: ['./wallet-card.component.scss']
})
export class WalletCardComponent {

  @Input() wallet?: Wallet;
  @Output() didClick= new EventEmitter<string>();
}

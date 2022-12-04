import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Wallet } from "src/app/models/wallet";


@Component({
  selector: 'app-wallet-list',
  templateUrl: './wallet-list.component.html',
  styleUrls: ['./wallet-list.component.scss']
})
export class WalletListComponent {

  @Input() wallets?: Wallet[];
  @Output() didClickItem = new EventEmitter<string>();

}

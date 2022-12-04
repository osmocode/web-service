import { Component, OnDestroy, OnInit } from "@angular/core";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalService } from "ng-zorro-antd/modal";
import { firstValueFrom, Subscription } from "rxjs";
import { Wallet } from "src/app/models/wallet";
import { AuthService } from "src/app/services/auth.service";
import { BankService } from "src/app/services/bank.service";
import { BankAddComponent } from "./add/bank-add.component";


@Component({
  selector: 'app-bank',
  templateUrl: './bank.component.html',
  styleUrls: ['./bank.component.scss']
})
export class BankComponent implements OnInit, OnDestroy {

  private readonly subscriptions: Subscription[] = [];
  wallets?: Wallet[];

  constructor(
    private readonly modalService: NzModalService,
    private readonly authService: AuthService,
    private readonly bankService: BankService,
    private readonly messageService: NzMessageService
  ) { }

  ngOnInit(): void {
    this.refresh();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  async addMoney(currency: string) {
    try {
      const user = await this.authService.current();
      if (user) {
        const wallet = await firstValueFrom(this.bankService.postBalance({
          uuid: user.id,
          currency: currency,
          value: 100
        }));
        this.messageService.info('Balance updated');
        this.refresh();
      } else {
        this.messageService.warning('Your session expired');
      }
    } catch (error) {
      this.messageService.error('Something get wrong');
    }
  }

  async removeMoney(currency: string) {
    try {
      const user = await this.authService.current();
      if (user) {
        const wallet = await firstValueFrom(this.bankService.removeBalance({
          uuid: user.id,
          currency: currency,
          value: 100
        }));
        this.messageService.info('Balance updated');
        this.refresh();
      } else {
        this.messageService.warning('Your session expired');
      }
    } catch (error) {
      this.messageService.error('Something get wrong');
    }
  }

  createWalletModal(): void {
    this.subscriptions.push(
      this.modalService.create({
        nzTitle: 'Add wallet',
        nzContent: BankAddComponent,
      }).afterClose.subscribe(() => {
        this.refresh();
      })
    );
  }

  private async refresh(): Promise<void> {
    try {
      const user = await this.authService.current();
      if (user) {
        const wallets = await firstValueFrom(this.bankService.getWallet({
          uuid: user.id
        }));
        this.wallets = wallets.wallets;
      } else {
        this.messageService.warning('Your session expired');
      }
    } catch (error) {
      this.messageService.error('Something get wrong');
    }
  }

}

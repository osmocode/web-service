import { Component, OnInit } from "@angular/core";
import { NzMessageService } from "ng-zorro-antd/message";
import { firstValueFrom } from "rxjs";
import { Article } from "src/app/models/sell";
import { SellService } from "src/app/services/sell.service";
import { TokenService } from "src/app/services/token.service";


@Component({
  selector: 'app-shop-cart',
  templateUrl: './shop-cart.component.html',
  styleUrls: ['./shop-cart.component.scss']
})
export class ShopCartComponent implements OnInit {

  currency: 'USD' | 'EURO' | 'YEN' = 'EURO';
  articles?: Article[];

  constructor(
    private readonly sellService: SellService,
    private readonly tokenService: TokenService,
    private readonly messageService: NzMessageService
  ) { }

  ngOnInit(): void {
    this.refresh();
  }

  private async refresh(): Promise<void> {
    try {
      const token = await this.tokenService.token();
      if (token) {
        const articles = await firstValueFrom(this.sellService.getBasket({
          token: token.token,
          currency: this.currency
        }));
        this.articles = articles.articles;
      } else {
        this.messageService.warning('Your session expired');
      }
    } catch (error) {
      this.messageService.error('Something get wrong');
    }
  }

}

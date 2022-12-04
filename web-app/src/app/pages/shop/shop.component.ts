import { ChangeDetectorRef, Component, OnDestroy, OnInit } from "@angular/core";
import { NzMessageService } from "ng-zorro-antd/message";
import { TokenService } from "src/app/services/token.service";
import { SellService } from "src/app/services/sell.service";
import { firstValueFrom, Subscription } from "rxjs";
import { Bike } from "src/app/models/bike";
import { Article } from "src/app/models/sell";
import { NzDrawerService } from "ng-zorro-antd/drawer";
import { ShopCartComponent } from "./cart/shop-cart.component";


@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.scss']
})
export class ShopComponent implements OnInit, OnDestroy {

  private readonly subscriptions: Subscription[] = [];
  articles?: Article[];
  _currency: 'EURO' | 'USD' | 'YEN' = 'EURO';

  get currency(): 'EURO' | 'USD' | 'YEN' {
    return this._currency;
  }

  set currency(value: 'EURO' | 'USD' | 'YEN') {
    this._currency = value;
    this.refresh();
  }

  constructor(
    private readonly tokenService: TokenService,
    private readonly messageService: NzMessageService,
    private readonly sellService: SellService,
    private readonly drawerService: NzDrawerService
  ) { }

  ngOnInit(): void {
    this.refresh();
  }

  ngOnDestroy(): void {
      this.subscriptions.forEach(s => s.unsubscribe());
  }

  createCartDrawer() {
    this.subscriptions.push(
      this.drawerService.create({
        nzTitle: 'Your cart',
        nzContent: ShopCartComponent,
        nzSize: 'large',
        nzContentParams: {
          currency: this._currency
        }
      }).afterClose.subscribe(() => {
        this.refresh();
      })
    );
  }

  async addToCart(uuid: string): Promise<void> {
    try {
      const token = await this.tokenService.token();
      if (token) {
        const article = await firstValueFrom(this.sellService.postBasket({
          token: token.token,
          bikeUuid: uuid,
          currency: 'EURO'
        }));
        this.messageService.success('Successfuly added on cart');
      } else {
        this.messageService.warning('Your session expired');
      }
    } catch (error) {
      this.messageService.error('Something get wrong');
    }
  }

  private async refresh(): Promise<void> {
    try {
      const token = await this.tokenService.token();
      if (token) {
        const articles = await firstValueFrom(this.sellService.getArticleList({
          token: token.token,
          currency: this._currency
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

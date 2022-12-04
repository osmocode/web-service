import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { firstValueFrom, Subscription } from "rxjs";
import { LoginToken } from "src/app/models/auth";
import { Bike } from "src/app/models/bike";
import { Customer } from "src/app/models/customer";
import { Feedback } from "src/app/models/feedback";
import { AuthService } from "src/app/services/auth.service";
import { BikeService } from "src/app/services/bike.service";
import { CustomerService } from "src/app/services/customer.service";
import { TokenService } from "src/app/services/token.service";


@Component({
  selector: 'app-customer-detail',
  templateUrl: './customer-detail.component.html',
  styleUrls: ['./customer-detail.component.scss']
})
export class CustomerDetailComponent implements OnInit {

  private readonly subscriptions: Subscription[] = [];
  private id!: string;

  token?: LoginToken;
  customer?: Customer;
  bikes?: Bike[];
  feedbacks?: Feedback[] = [];

  constructor(
    private readonly tokenService: TokenService,
    private readonly authService: AuthService,
    private readonly messageService: NzMessageService,
    private readonly customerService: CustomerService,
    private readonly bikeService: BikeService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly router: Router
  ) {
    this.subscriptions.push(
      this.activatedRoute.params.subscribe(params => {
        this.id = params['id'];
        this.onChangeRoute(params['id']);
      }),
    )
  }

  ngOnInit(): void {
    this.authService.current().then(async user => {
      if (user) {
        if (user.id === this.id) {
          this.token = await this.tokenService.token();
        }
      }
    })
  }

  private async onChangeRoute(id: string): Promise<void> {
    try {
      const user = await firstValueFrom(this.customerService.getCustomerById(id));
      this.customer = user;
      var bikes: Bike[] = [];
      user.bikes.forEach(async (bike) => {
        try {
          const b = await firstValueFrom(this.bikeService.getBikeById(bike));
          bikes.push(b);
        } catch (error) {
          this.messageService.error(`Error loading bike id: ${bike}`);
        }
      });
      this.bikes = bikes;
    } catch (error) {
      this.router.navigateByUrl('/error/404', { replaceUrl: true });
    }
  }

  initial(user: Customer): string {
    return (user.first_name.charAt(0) + user.last_name.charAt(0)).toUpperCase();
  }

  goDetailBike(id: string) {
    this.router.navigateByUrl(`/rental/${id}`, { replaceUrl: true });
  }

}

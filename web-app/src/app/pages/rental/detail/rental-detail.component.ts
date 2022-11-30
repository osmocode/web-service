import { Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalService } from "ng-zorro-antd/modal";
import { firstValueFrom, Subscription } from "rxjs";
import { Bike } from "src/app/models/bike";
import { Customer } from "src/app/models/customer";
import { Feedback } from "src/app/models/feedback";
import { PageResult } from "src/app/models/result";
import { AuthService } from "src/app/services/auth.service";
import { BikeService } from "src/app/services/bike.service";
import { RentalRentComponent } from "../rent/rental-rent.component";


@Component({
  selector: 'app-rental-detail',
  templateUrl: './rental-detail.component.html',
  styleUrls: ['./rental-detail.component.scss']
})
export class RentalDetailComponent implements OnInit {

  private readonly subscriptions: Subscription[] = [];
  private id!: string;

  bike?: Bike;
  canRent = false;

  constructor(
    private readonly authService: AuthService,
    private readonly bikeService: BikeService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly messageService: NzMessageService,
    private readonly modalService: NzModalService
  ) {
    this.subscriptions.push(
      this.activatedRoute.params.subscribe(params => {
        this.id = params['id'];
        this.onChangeRoute(params['id']);
      })
    );
  }

  createRentModal() {
    this.modalService.create({
      nzTitle: 'Rent bike',
      nzContent: RentalRentComponent,
      nzWidth: '60%'
    });
  }

  async ngOnInit(): Promise<void> {
    await this.authService.current().then((customer) => {
      if (customer) {
        this.canRent = !customer.bikes.includes(this.id);
      }
    })
  }

  private async onChangeRoute(id: string): Promise<void> {
    try {
      this.bike = await firstValueFrom(this.bikeService.getBikeById(id));
    } catch(error) {
      this.messageService.error('Something get wrong please try again later...');
    }
  }

}

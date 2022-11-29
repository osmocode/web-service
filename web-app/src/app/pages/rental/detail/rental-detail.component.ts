import { Component } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { firstValueFrom, Subscription } from "rxjs";
import { Bike } from "src/app/models/bike";
import { Feedback } from "src/app/models/feedback";
import { PageResult } from "src/app/models/result";
import { BikeService } from "src/app/services/bike.service";


@Component({
  selector: 'app-rental-detail',
  templateUrl: './rental-detail.component.html',
  styleUrls: ['./rental-detail.component.scss']
})
export class RentalDetailComponent {

  private readonly subscriptions: Subscription[] = [];
  private id!: string;

  bike?: Bike;

  constructor(
    private readonly bikeService: BikeService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly messageService: NzMessageService
  ) {
    this.subscriptions.push(
      this.activatedRoute.params.subscribe(params => {
        this.id = params['id'];
        this.onChangeRoute(params['id']);
      })
    );
  }

  private async onChangeRoute(id: string): Promise<void> {
    try {
      this.bike = await firstValueFrom(this.bikeService.getBikeById(id));
    } catch(error) {
      this.messageService.error('Something get wrong please try again later...');
    }
  }

}

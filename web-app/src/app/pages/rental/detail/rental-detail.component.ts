import { ChangeDetectorRef, Component, OnInit } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalService } from "ng-zorro-antd/modal";
import { firstValueFrom, Subscription } from "rxjs";
import { Bike } from "src/app/models/bike";
import { Customer } from "src/app/models/customer";
import { Feedback } from "src/app/models/feedback";
import { Rent } from "src/app/models/rent";
import { PageResult } from "src/app/models/result";
import { AuthService } from "src/app/services/auth.service";
import { BikeService } from "src/app/services/bike.service";
import { FeedbackService } from "src/app/services/feedback.service";
import { RentalService } from "src/app/services/rental.service";
import { RentalFeedbackComponent } from "../feedback/rental-feedback.component";
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
  rentals?: Rent[];
  feedbacks?: Feedback[];
  reviews: number = 0;
  average: number[] = [];
  canRent = false;

  constructor(
    private readonly feedbackService: FeedbackService,
    private readonly rentService: RentalService,
    private readonly authService: AuthService,
    private readonly bikeService: BikeService,
    private readonly activatedRoute: ActivatedRoute,
    private readonly messageService: NzMessageService,
    private readonly modalService: NzModalService,
  ) {
    this.subscriptions.push(
      this.activatedRoute.params.subscribe(params => {
        this.id = params['id'];
        this.onChangeRoute(params['id']);
      })
    );
  }

  createFeedbackModal(rental: Rent) {
    this.subscriptions.push(
      this.modalService.create({
        nzTitle: 'Add feedback',
        nzContent: RentalFeedbackComponent,
        nzWidth: '60%',
        nzComponentParams: {
          rental: rental
        }
      }).afterClose.subscribe(() => {
        this.onChangeRoute(this.id);
      })
    )

  }

  createRentModal() {
    this.modalService.create({
      nzTitle: 'Rent bike',
      nzContent: RentalRentComponent,
      nzWidth: '60%',
      nzComponentParams: {
        bike: this.bike,
        rentals: this.rentals
      }
    }).afterClose.subscribe(() => {
      this.onChangeRoute(this.id);
    });
  }

  async ngOnInit(): Promise<void> {
    await this.authService.current().then((customer) => {
      if (customer) {
        this.canRent = !customer.bikes.includes(this.id);
      }
    });
  }

  private async onChangeRoute(id: string): Promise<void> {
    try {
      const bike = await firstValueFrom(this.bikeService.getBikeById(id));
      this.reviews = 0;
      this.average = [];
      var feedbacks: Feedback[] = [];
      var rentals: Rent[] = [];
      bike.rent_queue.forEach(async queue => {
        try {
          const r = await firstValueFrom(this.rentService.getRentById(queue));
          rentals.push(r);
        } catch (error) {
          this.messageService.error(`Rent: ${queue} failed`);
        }
      });
      bike.rent_history.forEach(async feedback => {
        try {
          const f = await firstValueFrom(this.feedbackService.getFeedbackById(feedback));
          feedbacks.push(f);
          if (f.note !== 0) {
            this.reviews += 1;
            this.average.push(f.note);
          }
        } catch (error) {
          this.messageService.error(`Feedback: ${feedback} failed`);
        }
      });
      this.bike = bike;
      this.rentals = rentals;
      this.feedbacks = feedbacks;
    } catch(error) {
      this.messageService.error('Something get wrong please try again later...');
    }
  }

  averageStars(list: number[]): number {
    var sum = list.reduce((a, b) => a + b, 0);
    return (sum / list.length);
  }

}

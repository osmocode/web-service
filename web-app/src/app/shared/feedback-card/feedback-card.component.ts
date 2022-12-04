import { HttpClient } from "@angular/common/http";
import { Component, Input, OnInit } from "@angular/core";
import { NzMessageService } from "ng-zorro-antd/message";
import { firstValueFrom } from "rxjs";
import { Customer } from "src/app/models/customer";
import { Feedback } from "src/app/models/feedback";
import { Rent } from "src/app/models/rent";
import { CustomerService } from "src/app/services/customer.service";
import { RentalService } from "src/app/services/rental.service";


@Component({
  selector: 'app-feedback-card',
  templateUrl: './feedback-card.component.html',
  styleUrls: ['./feedback-card.component.scss']
})
export class FeedbackCardComponent implements OnInit {

  @Input() feedback!: Feedback;
  rental?: Rent;
  customer?: Customer;

  constructor(
    private readonly http: HttpClient,
    private readonly rentalService: RentalService,
    private readonly customerService: CustomerService,
    private readonly messageService: NzMessageService
  ) { }

  async ngOnInit(): Promise<void> {
    try {
      this.rental = await firstValueFrom(this.rentalService.getRentById(this.feedback.id));
      this.customer = await firstValueFrom(this.customerService.getCustomerById(this.rental.customer));
    } catch (error) {
      this.messageService.error(`Loading detail of ${this.feedback.id} failed`);
    }
  }

  initial(user?: Customer): string {
    if (user) {
      return (user.first_name.charAt(0) + user.last_name.charAt(0)).toUpperCase();
    }
    return 'A';
  }

}

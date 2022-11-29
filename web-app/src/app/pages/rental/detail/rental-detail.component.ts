import { Component } from "@angular/core";
import { Bike } from "src/app/models/bike";
import { Feedback } from "src/app/models/feedback";


@Component({
  selector: 'app-rental-detail',
  templateUrl: './rental-detail.component.html',
  styleUrls: ['./rental-detail.component.scss']
})
export class RentalDetailComponent {

  bike?: Bike;
  feedbacks?: Feedback[];

}

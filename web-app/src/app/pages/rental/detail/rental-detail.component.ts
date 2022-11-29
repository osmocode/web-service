import { Component } from "@angular/core";
import { Bike } from "src/app/models/bike";
import { Feedback } from "src/app/models/feedback";


@Component({
  selector: 'app-rental-detail',
  templateUrl: './rental-detail.component.html',
  styleUrls: ['./rental-detail.component.scss']
})
export class RentalDetailComponent {

  bike: Bike = {
    id: "1",
    label: "Super bike",
    desc: "New bike with the best performance."
  }

  feedbacks: Feedback[] = [
    {
      title: "Super",
      desc: "Lorem ipsum"
    },
    {
      title: "Super",
      desc: "Lorem ipsum"
    },
    {
      title: "Super",
      desc: "Lorem ipsum"
    }
  ]
}

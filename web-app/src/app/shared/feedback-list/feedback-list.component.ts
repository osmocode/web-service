import { Component, Input } from "@angular/core";
import { Feedback } from "src/app/models/feedback";


@Component({
  selector: 'app-feedback-list',
  templateUrl: './feedback-list.component.html',
  styleUrls: ['./feedback-list.component.scss']
})
export class FeedbackListComponent {

  @Input() feedbacks?: Feedback[];

}

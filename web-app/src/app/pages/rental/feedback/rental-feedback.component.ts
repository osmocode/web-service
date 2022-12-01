import { Component, Input } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalRef } from "ng-zorro-antd/modal";
import { lastValueFrom } from "rxjs";
import { BikeState } from "src/app/models/bike";
import { Rent } from "src/app/models/rent";
import { FeedbackService } from "src/app/services/feedback.service";


@Component({
  selector: 'app-rent-feedback',
  templateUrl: './rental-feedback.component.html',
  styleUrls: ['./rental-feedback.component.scss']
})
export class RentalFeedbackComponent {

  @Input() rental!: Rent;

  form!: FormGroup;
  bikeState = BikeState;
  submiting = false;

  constructor(
    private readonly modal: NzModalRef,
    private readonly formBuilder: FormBuilder,
    private readonly feedbackService: FeedbackService,
    private readonly messageService: NzMessageService
  ) {
    this.form = this.formBuilder.group({
      note: [0, []],
      comment: ['', []],
      bike_state: ['', []]
    });
  }

  destroyModal(): void {
    this.modal.destroy();
  }

  async submitModal(): Promise<void> {
    this.submiting = true;
    if (this.form.valid) {
      try {
        const feedback = await lastValueFrom(this.feedbackService.postFeedback(this.rental.id, this.form.value));
        this.messageService.success(`Success`);
        this.submiting = false;
        this.modal.destroy();
      } catch (error) {
        this.messageService.error(`Something get wrong...`);
      }
    } else {
      this.submiting = false;
    }
  }

}

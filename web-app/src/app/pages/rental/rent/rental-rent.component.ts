import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalRef } from "ng-zorro-antd/modal";
import { BikeService } from "src/app/services/bike.service";


@Component({
  selector: 'app-rental-rent',
  templateUrl: './rental-rent.component.html',
  styleUrls: ['./rental-rent.component.scss']
})
export class RentalRentComponent {

  form!: FormGroup;
  submiting = false;

  constructor(
    private readonly modal: NzModalRef,
    private readonly formBuilder: FormBuilder,
    private readonly http: HttpClient,
    private readonly bikeService: BikeService,
    private readonly messageService: NzMessageService
  ) {
    this.form = this.formBuilder.group({
      rangePicker: [[Validators.required]]
    });
  }

  disableDate = (d: Date) => {

  }

  destroyModal() {
    this.modal.destroy();
  }

  async submitModal() {
    this.submiting = true;
    if (this.form.valid) {

    } else {
      Object.values(this.form.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
      this.submiting = false;
    }
  }

}

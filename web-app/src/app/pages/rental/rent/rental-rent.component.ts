import { HttpClient } from "@angular/common/http";
import { Component, Input, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalRef } from "ng-zorro-antd/modal";
import { Bike } from "src/app/models/bike";
import { Rent } from "src/app/models/rent";
import { BikeService } from "src/app/services/bike.service";

import { differenceInCalendarDays, parseISO } from 'date-fns'
import { RentalService } from "src/app/services/rental.service";
import { firstValueFrom } from "rxjs";

@Component({
  selector: 'app-rental-rent',
  templateUrl: './rental-rent.component.html',
  styleUrls: ['./rental-rent.component.scss']
})
export class RentalRentComponent {

  @Input() bike!: Bike;
  @Input() rentals!: Rent[];

  form!: FormGroup;
  submiting = false;
  today = new Date();

  constructor(
    private readonly modal: NzModalRef,
    private readonly formBuilder: FormBuilder,
    private readonly http: HttpClient,
    private readonly bikeService: BikeService,
    private readonly rentService: RentalService,
    private readonly messageService: NzMessageService
  ) {
    this.form = this.formBuilder.group({
      rangePicker: [[], Validators.required]
    });
  }

  disableDate = (current: Date): boolean => {
    if (differenceInCalendarDays(current, this.today) < 0) {
      return true;
    }

    for (let index = 0; index < this.rentals.length; index++) {
      const element = this.rentals[index];
      if (differenceInCalendarDays(current, new Date(element.start)) >= 0 &&
          differenceInCalendarDays(current, new Date(element.end)) <= 0) {
            return true;
          }
    }
    return false;
  }

  destroyModal() {
    this.modal.destroy();
  }

  async submitModal() {
    this.submiting = true;
    if (this.form.valid) {
      try {
        await firstValueFrom(this.rentService.postRent({
          start: this.form.value.rangePicker[0],
          end: this.form.value.rangePicker[1],
          bike: this.bike
        }));
        this.messageService.success('You rent the bike!');
        this.modal.destroy();
      } catch (error) {
        this.messageService.error('Rent failed...');
      }
    } else {
      Object.values(this.form.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
    this.submiting = false;
  }

}

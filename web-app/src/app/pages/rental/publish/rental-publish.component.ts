import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalRef } from "ng-zorro-antd/modal";
import { NzUploadFile } from "ng-zorro-antd/upload";
import { lastValueFrom, map, Observable, toArray } from "rxjs";
import { BikeState } from "src/app/models/bike";
import { BikeService } from "src/app/services/bike.service";


@Component({
  selector: 'app-rental-publish',
  templateUrl: './rental-publish.component.html',
  styleUrls: ['./rental-publish.component.scss']
})
export class RentalPublishComponent {

  form!: FormGroup;
  bikeState = BikeState;
  submiting = false;

  constructor(
    private readonly modal: NzModalRef,
    private readonly formBuilder: FormBuilder,
    private readonly http: HttpClient,
    private readonly bikeService: BikeService,
    private readonly messageService: NzMessageService
  ) {
    this.form = this.formBuilder.group({
      label: ['', [Validators.required]],
      desc: ['', []],
      state: ['', [Validators.required]]
    });
  }

  destroyModal() {
    this.modal.destroy();
  }

  async submitModal() {
    this.submiting = true;
    if (this.form.valid) {
      try {
        const bike = await lastValueFrom(this.bikeService.post(this.form.value));
        this.messageService.success(`Bike with label: ${bike.label} created!`);
        this.submiting = false;
        this.modal.destroy();
      } catch (error) {
        this.messageService.error(`Bike creation failed...`);
      }
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

  previewFile = (file: NzUploadFile): Observable<string> => {
    console.log('Your upload file:', file);
    return this.http
      .post<{ thumbnail: string }>(`https://next.json-generator.com/api/json/get/4ytyBoLK8`, {
        method: 'POST',
        body: file
      })
      .pipe(map(res => res.thumbnail));
  };

}

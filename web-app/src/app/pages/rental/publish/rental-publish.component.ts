import { HttpClient } from "@angular/common/http";
import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzModalRef } from "ng-zorro-antd/modal";
import { NzUploadFile } from "ng-zorro-antd/upload";
import { map, Observable } from "rxjs";


@Component({
  selector: 'app-rental-publish',
  templateUrl: './rental-publish.component.html',
  styleUrls: ['./rental-publish.component.scss']
})
export class RentalPublishComponent {

  form!: FormGroup;

  constructor(
    private readonly modal: NzModalRef,
    private readonly formBuilder: FormBuilder,
    private readonly http: HttpClient
  ) {
    this.form = this.formBuilder.group({
      label: ['', [Validators.required]],
      desc: ['', []]
    });
  }

  destroyModal() {
    this.modal.destroy();
  }

  submitModal() {

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

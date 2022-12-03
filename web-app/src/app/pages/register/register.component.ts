import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { lastValueFrom } from "rxjs";
import { CustomerType } from "src/app/models/customer";
import { CustomerService } from "src/app/services/customer.service";


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  form: FormGroup;
  userType = CustomerType;
  submiting = false;

  constructor(
    private readonly customerService: CustomerService,
    private readonly formBuilder: FormBuilder,
    private readonly messageService: NzMessageService,
    private readonly router: Router,
  ) {
    this.form = this.formBuilder.group({
      first_name: [null, [Validators.required]],
      last_name: [null, [Validators.required]],
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      type: [null, [Validators.required]]
    });
  }

  async submitForm() {
    this.submiting = true;
    if (this.form.valid) {
      try {
        const user = await lastValueFrom(this.customerService.postCustomer(this.form.value))
        this.messageService.success(`User ${user.username} created !`);
        this.router.navigateByUrl('/login', { replaceUrl: true });
      } catch (error) {
        this.messageService.error('Something get wrong, try again later...');
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

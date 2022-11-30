import { Component } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { NzMessageService } from "ng-zorro-antd/message";
import { firstValueFrom, lastValueFrom } from "rxjs";
import { AuthService } from "src/app/services/auth.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  form: FormGroup;

  constructor(
    private readonly messageService: NzMessageService,
    private readonly formBuilder: FormBuilder,
    private readonly authService: AuthService,
    private readonly router: Router
  ) {
    this.form = this.formBuilder.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  async submitForm() {
    if (this.form.valid) {
      this.authService.login(this.form.value).then((user) => {
        this.messageService.success(`Successfuly connected ${user.first_name} ${user.last_name}!`);
        this.router.navigateByUrl('/home', { replaceUrl: true });
      }).catch((error) => {
        this.messageService.error('Connexion failed...');
      });
    } else {
      Object.values(this.form.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

}

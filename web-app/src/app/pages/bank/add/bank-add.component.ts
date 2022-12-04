import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalRef } from "ng-zorro-antd/modal";
import { firstValueFrom } from "rxjs";
import { AuthService } from "src/app/services/auth.service";
import { BankService } from "src/app/services/bank.service";


@Component({
  selector: 'app-bank-add',
  templateUrl: './bank-add.component.html',
  styleUrls: ['./bank-add.component.scss']
})
export class BankAddComponent implements OnInit {

  form!: FormGroup;
  submiting: boolean = false;

  constructor(
    private readonly modal: NzModalRef,
    private readonly bankService: BankService,
    private readonly authService: AuthService,
    private readonly formBuilder: FormBuilder,
    private readonly messageService: NzMessageService
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      currency: ['EURO', [Validators.required]],
      value: [0, [Validators.required]],
    });
  }

  destroyModal() {
    this.modal.destroy();
  }

  async submitModal(): Promise<void> {
    this.submiting = true;
    if (this.form.valid) {
      const user = await this.authService.current();
      if (user) {
        const wallet = await firstValueFrom(this.bankService.postBalance({
          uuid: user.id,
          currency: this.form.value.currency,
          value: this.form.value.value
        }));
        this.messageService.success('Wallet was created with succed');
        this.modal.destroy();
      } else {
        this.messageService.warning('Your session expired');
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

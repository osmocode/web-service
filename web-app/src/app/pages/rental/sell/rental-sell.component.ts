import { Component, Input } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { NzMessageService } from "ng-zorro-antd/message";
import { NzModalRef } from "ng-zorro-antd/modal";
import { firstValueFrom } from "rxjs";
import { Bike } from "src/app/models/bike";
import { AuthService } from "src/app/services/auth.service";
import { SellService } from "src/app/services/sell.service";
import { TokenService } from "src/app/services/token.service";


@Component({
  selector: 'app-rental-sell',
  templateUrl: './rental-sell.component.html',
  styleUrls: ['./rental-sell.component.scss']
})
export class RentalSellComponent {

  @Input() bike!: Bike;
  form!: FormGroup;
  submiting: boolean = false;

  constructor(
    private readonly modal: NzModalRef,
    private readonly formBuilder: FormBuilder,
    private readonly messageService: NzMessageService,
    private readonly tokenService: TokenService,
    private readonly sellService: SellService
  ) {
    this.form = this.formBuilder.group({
      currency: [null, [Validators.required]],
      price: [1, [Validators.required]]
    });
  }

  destroyModal() {
    this.modal.destroy();
  }

  async submitModal() {
    this.submiting = true;
    if (this.form.valid) {
      try {
        const token = await this.tokenService.token();
        if (token) {
          const article = await firstValueFrom(this.sellService.postArticle({
            token: token.token,
            uuid: this.bike.id,
            price: this.form.value.price,
            currency: this.form.value.currency
          }));
          console.log(article);
          this.modal.destroy();
          this.messageService.success(`Your bike is added for sell!`);
        } else {
          this.messageService.error(`Something get wrong...`);
        }
      } catch (error) {
        this.messageService.error(`Add bike to sell failed!`);
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

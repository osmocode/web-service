import { Component, EventEmitter, Input, OnInit, Output, ViewContainerRef } from "@angular/core";
import { Router } from "@angular/router";
import { NzModalService } from "ng-zorro-antd/modal";
import { firstValueFrom } from "rxjs";
import { Customer } from "src/app/models/customer";
import { Rent } from "src/app/models/rent";
import { CustomerService } from "src/app/services/customer.service";

@Component({
  selector: 'app-rental-card',
  templateUrl: './rental-card.component.html',
  styleUrls: ['./rental-card.component.scss']
})
export class RentalCardComponent implements OnInit {

  @Input() rental!: Rent;
  @Output() didClick = new EventEmitter<void>();

  user?: Customer;

  constructor(
    private readonly modalService: NzModalService,
    private readonly customerService: CustomerService,
    private readonly router: Router
  ) { }

  async ngOnInit(): Promise<void> {
    this.user = await firstValueFrom(this.customerService.getCustomerById(this.rental.customer));
  }

  initial(user: Customer): string {
    return (user.first_name.charAt(0) + user.last_name.charAt(0)).toUpperCase();
  }

  goCustomerDetail(id: string) {
    this.router.navigateByUrl(`/customer/${id}`, { replaceUrl: true });
  }

}

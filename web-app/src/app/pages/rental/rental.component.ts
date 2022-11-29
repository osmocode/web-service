import { Component } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NzModalService } from "ng-zorro-antd/modal";
import { Bike } from "src/app/models/bike";
import { RentalPublishComponent } from "./publish/rental-publish.component";


@Component({
  selector: 'app-rental',
  templateUrl: './rental.component.html',
  styleUrls: ['./rental.component.scss']
})
export class RentalComponent {

  bikes: Bike[] = [
    {
      id: "1",
      label: "Super bike",
      desc: "New bike with the best performance."
    },
    {
      id: "1",
      label: "Super bike",
      desc: "New bike with the best performance."
    },
    {
      id: "1",
      label: "Super bike",
      desc: "New bike with the best performance."
    },
    {
      id: "1",
      label: "Super bike",
      desc: "New bike with the best performance."
    }
  ]

  constructor(
    private readonly modalService: NzModalService,
    private readonly activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  changeRoute(id: string) {
    this.router.navigate([id], { relativeTo: this.activatedRoute });
  }

  createPublishModal() {
    this.modalService.create({
      nzTitle: 'Publish bike',
      nzContent: RentalPublishComponent,
      nzWidth: '60%'
    })
  }

}

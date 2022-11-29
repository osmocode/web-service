import { Component, OnDestroy, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NzModalService } from "ng-zorro-antd/modal";
import { Subscription } from "rxjs";
import { Bike } from "src/app/models/bike";
import { PageResult } from "src/app/models/result";
import { BikeService } from "src/app/services/bike.service";
import { RentalPublishComponent } from "./publish/rental-publish.component";


@Component({
  selector: 'app-rental',
  templateUrl: './rental.component.html',
  styleUrls: ['./rental.component.scss']
})
export class RentalComponent implements OnInit, OnDestroy{

  private readonly _subscriptions: Subscription[] = [];

  pageResult?: PageResult<Bike>;

  constructor(
    private readonly bikeService: BikeService,
    private readonly modalService: NzModalService,
    private readonly activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this._subscriptions.push(
      this.bikeService.getAll().subscribe((pageResult) => {
        this.pageResult = pageResult;
      })
    );
  }

  ngOnDestroy(): void {
    this._subscriptions.forEach(s => s.unsubscribe());
  }

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

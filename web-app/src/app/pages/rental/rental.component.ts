import { Component, OnDestroy, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { NzModalService } from "ng-zorro-antd/modal";
import { lastValueFrom, Subscription } from "rxjs";
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

  private readonly subscriptions: Subscription[] = [];

  pageResult?: PageResult<Bike>;

  constructor(
    private readonly bikeService: BikeService,
    private readonly modalService: NzModalService,
    private readonly activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  async ngOnInit(): Promise<void> {
    this.pageResult = await lastValueFrom(this.bikeService.getAll());
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe());
  }

  changeRoute(id: string) {
    this.router.navigate([id], { relativeTo: this.activatedRoute });
  }

  createPublishModal() {
    this.subscriptions.push(
      this.modalService.create({
        nzTitle: 'Publish bike',
        nzContent: RentalPublishComponent,
        nzWidth: '60%',
      }).afterClose.subscribe(async () => {
        this.pageResult = await lastValueFrom(this.bikeService.getAll())
      })
    )

  }

}

import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { NzGridModule } from "ng-zorro-antd/grid";
import { SharedComponentModule } from "src/app/shared/shared.module";
import { RentalDetailComponent } from "./detail/rental-detail.component";
import { RentalComponent } from "./rental.component";
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzRateModule } from 'ng-zorro-antd/rate';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NzPageHeaderModule } from 'ng-zorro-antd/page-header';
import { NzIconModule } from "ng-zorro-antd/icon";
import { NzStatisticModule } from 'ng-zorro-antd/statistic';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { RentalPublishComponent } from "./publish/rental-publish.component";


const routes: Routes = [
  {
    path: '',
    component: RentalComponent
  },
  {
    path: ':id',
    component: RentalDetailComponent
  },
  { path: '**', pathMatch: 'full', redirectTo: ''}
]

@NgModule({
  declarations: [
    RentalComponent,
    RentalDetailComponent,
    RentalPublishComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NzFormModule,
    NzInputModule,
    NzModalModule,
    NzStatisticModule,
    NzIconModule,
    NzPageHeaderModule,
    NzRateModule,
    NzDividerModule,
    NzGridModule,
    NzButtonModule,
    NzUploadModule,
    SharedComponentModule,
    RouterModule.forChild(routes)
  ]
})
export class RentalModule { }
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { NzAvatarModule } from "ng-zorro-antd/avatar";
import { NzDividerModule } from "ng-zorro-antd/divider";
import { NzGridModule } from "ng-zorro-antd/grid";
import { SharedComponentModule } from "src/app/shared/shared.module";
import { CustomerComponent } from "./customer.component";
import { CustomerDetailComponent } from "./detail/customer-detail.component";
import { NzStatisticModule } from 'ng-zorro-antd/statistic';
import { NzMessageModule } from "ng-zorro-antd/message";


const routes: Routes = [
  {
    path: '',
    component: CustomerComponent
  },
  {
    path: ':id',
    component: CustomerDetailComponent
  },
  { path: '**', pathMatch: 'full', redirectTo: '/error/404' }
]

@NgModule({
  declarations: [
    CustomerComponent,
    CustomerDetailComponent
  ],
  imports: [
    CommonModule,
    NzGridModule,
    NzAvatarModule,
    NzDividerModule,
    NzStatisticModule,
    NzMessageModule,
    SharedComponentModule,
    RouterModule.forChild(routes)
  ]
})
export class CustomerModule { }

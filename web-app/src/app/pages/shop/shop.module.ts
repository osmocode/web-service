import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { NzButtonModule } from "ng-zorro-antd/button";
import { NzDatePickerModule } from "ng-zorro-antd/date-picker";
import { NzDividerModule } from "ng-zorro-antd/divider";
import { NzDropDownModule } from "ng-zorro-antd/dropdown";
import { NzFormModule } from "ng-zorro-antd/form";
import { NzGridModule } from "ng-zorro-antd/grid";
import { NzIconModule } from "ng-zorro-antd/icon";
import { NzInputModule } from "ng-zorro-antd/input";
import { NzInputNumberModule } from "ng-zorro-antd/input-number";
import { NzMessageModule } from "ng-zorro-antd/message";
import { NzModalModule } from "ng-zorro-antd/modal";
import { NzPageHeaderModule } from "ng-zorro-antd/page-header";
import { NzRateModule } from "ng-zorro-antd/rate";
import { NzSelectModule } from "ng-zorro-antd/select";
import { NzStatisticModule } from "ng-zorro-antd/statistic";
import { NzTypographyModule } from "ng-zorro-antd/typography";
import { NzUploadModule } from "ng-zorro-antd/upload";
import { SharedComponentModule } from "src/app/shared/shared.module";
import { ShopComponent } from "./shop.component";
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { ShopCartComponent } from "./cart/shop-cart.component";


const routes: Routes = [
  {
    path: '',
    component: ShopComponent
  },
  { path: '**', pathMatch: 'full', redirectTo: ''}
]

@NgModule({
  declarations: [
    ShopComponent,
    ShopCartComponent
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
    NzMessageModule,
    NzDropDownModule,
    NzSelectModule,
    NzDatePickerModule,
    NzInputNumberModule,
    NzTypographyModule,
    NzDrawerModule,
    SharedComponentModule,
    RouterModule.forChild(routes)
  ]
})
export class ShopModule {

}

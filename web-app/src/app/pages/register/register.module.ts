import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { NzButtonModule } from "ng-zorro-antd/button";
import { NzFormModule } from "ng-zorro-antd/form";
import { NzGridModule } from "ng-zorro-antd/grid";
import { NzIconModule } from "ng-zorro-antd/icon";
import { NzInputModule } from "ng-zorro-antd/input";
import { NzMessageModule, NzMessageService } from "ng-zorro-antd/message";
import { NzSelectModule } from "ng-zorro-antd/select";
import { SharedComponentModule } from "src/app/shared/shared.module";
import { RegisterComponent } from "./register.component";

const routes: Routes = [
  {
    path: '',
    component: RegisterComponent
  },
  { path: '**', pathMatch: 'full', redirectTo: '' }
]

@NgModule({
  declarations: [
    RegisterComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    NzInputModule,
    NzButtonModule,
    NzFormModule,
    NzIconModule,
    NzGridModule,
    NzSelectModule,
    NzMessageModule,
    SharedComponentModule,
    RouterModule.forChild(routes)
  ]
})
export class RegisterModule { }

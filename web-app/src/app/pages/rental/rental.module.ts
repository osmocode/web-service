import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SharedComponentModule } from "src/app/shared/shared.module";
import { RentalComponent } from "./rental.component";

const routes: Routes = [
  {
    path: '',
    component: RentalComponent
  },
  { path: '**', pathMatch: 'full', redirectTo: ''}
]

@NgModule({
  declarations: [
    RentalComponent
  ],
  imports: [
    CommonModule,
    SharedComponentModule,
    RouterModule.forChild(routes)
  ]
})
export class RentalModule { }

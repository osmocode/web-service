import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SharedComponentModule } from "src/app/shared/shared.module";
import { HomeComponent } from "./home.component";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  { path: '**', pathMatch: 'full', redirectTo: ''}
]

@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    CommonModule,
    SharedComponentModule,
    RouterModule.forChild(routes)
  ]
})
export class HomeModule { }

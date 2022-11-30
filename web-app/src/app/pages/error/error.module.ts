import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { SharedComponentModule } from "src/app/shared/shared.module";
import { NotAuthorizedErrorComponent } from "./403error/403error.component";
import { NotFoundErrorComponent } from "./404error/404error.component";
import { InternalErrorComponent } from "./500error/500error.component";
import { NzResultModule } from 'ng-zorro-antd/result';
import { NzButtonModule } from "ng-zorro-antd/button";


const routes: Routes = [
  {
    path: '404',
    component: NotFoundErrorComponent
  },
  {
    path: '403',
    component: NotAuthorizedErrorComponent
  },
  {
    path: '500',
    component: InternalErrorComponent
  },
  { path: '**', pathMatch: 'full', redirectTo: '404' }
]

@NgModule({
  declarations: [
    NotFoundErrorComponent,
    NotAuthorizedErrorComponent,
    InternalErrorComponent
  ],
  imports: [
    CommonModule,
    NzResultModule,
    NzButtonModule,
    SharedComponentModule,
    RouterModule.forChild(routes)
  ]
})
export class ErrorModule { }

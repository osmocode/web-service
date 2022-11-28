import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";

// APP-Modules
import { LayoutComponent } from "./layout/layout.component";
import { ContainerComponent } from "./container/container.component";
import { ContentComponent } from "./content/content.component";
import { BikeListComponent } from "./bike-list/bike-list.component";

// NZ-Modules
import { NzLayoutModule } from "ng-zorro-antd/layout";
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzIconModule } from "ng-zorro-antd/icon";
import { NzMenuModule } from "ng-zorro-antd/menu";
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzCardModule } from 'ng-zorro-antd/card';
import { BikeCardComponent } from "./bike-card/bike-card.component";


const SHARED_COMPONENTS = [
  LayoutComponent,
  ContainerComponent,
  ContentComponent,
  BikeListComponent,
  BikeCardComponent
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    NzButtonModule,
    NzIconModule,
    NzMenuModule,
    NzLayoutModule,
    NzGridModule,
    NzCardModule
  ],
  declarations: [
    ...SHARED_COMPONENTS
  ],
  exports: [
    ...SHARED_COMPONENTS
  ]
})
export class SharedComponentModule {}

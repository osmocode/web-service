import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

// APP-Modules
import { LayoutComponent } from "./layout/layout.component";
import { ContainerComponent } from "./container/container.component";
import { ContentComponent } from "./content/content.component";
import { BikeListComponent } from "./bike-list/bike-list.component";
import { BikeCardComponent } from "./bike-card/bike-card.component";
import { FeedbackCardComponent } from "./feedback-card/feedback-card.component";
import { FeedbackListComponent } from "./feedback-list/feedback-list.component";
import { RentalListComponent } from "./rental-list/rental-list.component";
import { RentalCardComponent } from "./rental-card/rental-card.component";
import { ArticleListComponent } from "./article-list/article-list.component";
import { ArticleCardComponent } from "./article-card/article-card.component";
import { WalletListComponent } from "./wallet-list/wallet-list.component";
import { WalletCardComponent } from "./wallet-card/wallet-card.component";

// NZ-Modules
import { NzLayoutModule } from "ng-zorro-antd/layout";
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzIconModule } from "ng-zorro-antd/icon";
import { NzMenuModule } from "ng-zorro-antd/menu";
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzCommentModule } from 'ng-zorro-antd/comment';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzRateModule } from "ng-zorro-antd/rate";
import { NzTimelineModule } from 'ng-zorro-antd/timeline';
import { NzEmptyModule } from 'ng-zorro-antd/empty';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzSkeletonModule } from 'ng-zorro-antd/skeleton';
import { NzStatisticModule } from 'ng-zorro-antd/statistic';
import { NzSelectModule } from "ng-zorro-antd/select";
import { NzFormModule } from "ng-zorro-antd/form";
import { NzInputModule } from "ng-zorro-antd/input";
import { NzTagModule } from 'ng-zorro-antd/tag';


const SHARED_COMPONENTS = [
  LayoutComponent,
  ContainerComponent,
  ContentComponent,
  BikeListComponent,
  BikeCardComponent,
  FeedbackListComponent,
  FeedbackCardComponent,
  RentalListComponent,
  RentalCardComponent,
  ArticleListComponent,
  ArticleCardComponent,
  WalletListComponent,
  WalletCardComponent
]

@NgModule({
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ReactiveFormsModule,

    NzButtonModule,
    NzIconModule,
    NzMenuModule,
    NzLayoutModule,
    NzGridModule,
    NzCardModule,
    NzCommentModule,
    NzAvatarModule,
    NzRateModule,
    NzTimelineModule,
    NzEmptyModule,
    NzSpinModule,
    NzDropDownModule,
    NzSkeletonModule,
    NzStatisticModule,
    NzSelectModule,
    NzFormModule,
    NzInputModule,
    NzTagModule,
  ],
  declarations: [
    ...SHARED_COMPONENTS
  ],
  exports: [
    ...SHARED_COMPONENTS
  ]
})
export class SharedComponentModule {}

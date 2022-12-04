import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { PreloadAllModules, RouterModule, Routes } from "@angular/router";
import { CustomerGuard } from "./guards/customer.guard";
import { LoginGuard } from "./guards/login.guard";

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import(
      './pages/home/home.module'
    ).then(m => m.HomeModule)
  },
  {
    path: 'login',
    canActivate: [LoginGuard],
    loadChildren: () => import(
      './pages/login/login.module'
    ).then(m => m.LoginModule)
  },
  {
    path: 'register',
    loadChildren: () => import(
      './pages/register/register.module'
    ).then(m => m.RegisterModule)
  },
  {
    path: 'customer',
    canActivate: [CustomerGuard],
    data: { rules: ['all'] },
    loadChildren: () => import(
      './pages/customer/customer.module'
    ).then(m => m.CustomerModule)
  },
  {
    path: 'rental',
    canActivate: [CustomerGuard],
    data: { rules: ['all'] },
    loadChildren: () => import(
      './pages/rental/rental.module'
    ).then(m => m.RentalModule)
  },
  {
    path: 'shop',
    canActivate: [CustomerGuard],
    data: { rules: ['all'] },
    loadChildren: () => import(
      './pages/shop/shop.module'
    ).then(m => m.ShopModule)
  },
  {
    path: 'error',
    loadChildren: () => import(
      './pages/error/error.module'
    ).then(m => m.ErrorModule)
  },
  { path: '**', pathMatch: 'full', redirectTo: '/home' }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, {
      enableTracing: true,
      preloadingStrategy: PreloadAllModules
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}

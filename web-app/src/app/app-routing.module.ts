import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { PreloadAllModules, RouterModule, Routes } from "@angular/router";

const routes: Routes = [
  {
    path: 'home',
    loadChildren: () => import(
      './pages/home/home.module'
    ).then(m => m.HomeModule)
  },
  {
    path: 'login',
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
    path: 'rental',
    loadChildren: () => import(
      './pages/rental/rental.module'
    ).then(m => m.RentalModule)
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

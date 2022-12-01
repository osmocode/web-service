import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { CustomerType } from "../models/customer";
import { AuthService } from "../services/auth.service";


@Injectable({
  providedIn: 'root'
})
export class CustomerGuard implements CanActivate {


  constructor(
    private readonly router: Router,
    private readonly authService: AuthService
  ) { }

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const user = await this.authService.current();

    if (user) {
      const rules: (CustomerType | 'all')[] = route.data['rules'];
      if (rules.includes('all')) {
        return true;
      }
      if (rules.includes(user.type)) {
        return true;
      }
    }

    return this.router.navigateByUrl('/error/403', { replaceUrl: true });
  }
}

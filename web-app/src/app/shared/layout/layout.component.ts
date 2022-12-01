import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { firstValueFrom, Subscription } from "rxjs";
import { Customer } from "src/app/models/customer";
import { AuthService } from "src/app/services/auth.service";


@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class LayoutComponent implements OnInit {

  user?: Customer;

  constructor(
    private readonly authService: AuthService,
    private readonly router: Router,
    private readonly changeDetectorRef: ChangeDetectorRef
  ) { }

  async ngOnInit(): Promise<void> {
    this.user = await this.authService.current();
    this.changeDetectorRef.markForCheck();
  }

  initial(user: Customer): string {
    return (user.first_name.charAt(0) + user.last_name.charAt(0)).toUpperCase();
  }

  goProfile(user: Customer): void {
    this.router.navigateByUrl(`/customer/${user.id}`, { replaceUrl: true });
  }

  logout() {
    this.authService.logout().then(() => {
      this.router.navigateByUrl('/', { replaceUrl: true });
      this.user = undefined;
      this.changeDetectorRef.markForCheck();
    });
  }

}

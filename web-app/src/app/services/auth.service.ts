import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom, Observable } from "rxjs";
import { LoginForm, LoginToken } from "../models/auth";
import { Customer } from "../models/customer";
import { TokenService } from "./token.service";


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private readonly http: HttpClient,
    private readonly tokenService: TokenService
  ) { }

  async login(form: LoginForm): Promise<Customer> {
    await this.tokenService.obtain(form.username, form.password);
    return lastValueFrom(this.http.get<Customer>(`/api/v1/auth/me`));
  }

  async logout(): Promise<void> {
    await lastValueFrom(this.http.post(`/api/v1/auth/logout`, {}));
    await this.tokenService.remove();
  }

  async current(): Promise<Customer | undefined> {
    const token = await this.tokenService.token();
    if (token) {
      try {
        return await lastValueFrom(this.http.get<Customer>(`/api/v1/auth/me`));
      } catch {
        await this.tokenService.remove();
      }
    }
    return undefined;
  }

}

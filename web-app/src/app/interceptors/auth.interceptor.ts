import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { from, Observable, switchMap } from "rxjs";
import { LoginToken } from "../models/auth";
import { TokenService } from "../services/token.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor {


  constructor(
    private readonly tokenService: TokenService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return from(this.tokenService.token()).pipe(
      switchMap(token => {
        if (token) {
          return next.handle(this.addHeader(request, token));
        }
        return next.handle(request);
      })
    )
  }

  private addHeader(
    request: HttpRequest<any>,
    token: LoginToken
  ): HttpRequest<any> {
    return request.clone({
      headers: request.headers.set('X-Auth-Token', token.token)
    });
  }
}

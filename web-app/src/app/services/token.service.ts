import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { lastValueFrom, Observable } from "rxjs";
import { LoginForm, LoginToken } from "../models/auth";


@Injectable({
  providedIn: 'root'
})
export class TokenService {


  constructor(
    private readonly http: HttpClient,
  ) { }

  token(): Promise<LoginToken | undefined> {
    return new Promise((resolve, reject) => {
      var token = localStorage.getItem('token');
      if (token) {
        resolve({ token: token });
      } else {
        resolve(undefined);
      }
    })
  }

  remove(): Promise<void> {
    return new Promise((resolve, reject) => {
      resolve(localStorage.removeItem('token'));
    });
  }

  async obtain(username: string, password: string): Promise<LoginToken> {
    const token = await lastValueFrom(this.http.post<LoginToken>(`/api/v1/auth/login`, {
      username: username,
      password: password
    }));

    localStorage.setItem('token', token.token);

    return token;
  }


}

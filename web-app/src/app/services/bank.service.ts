import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Wallet, WalletAdd, WalletGet, WalletList, WalletListGet, WalletRemove } from "../models/wallet";


@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(
    private readonly http: HttpClient
  ) { }

  getWallet(form: WalletListGet): Observable<WalletList> {
    return this.http.post<WalletList>(`/api/v2/bank/getWalletList`, form);
  }

  getBalance(form: WalletGet): Observable<Wallet> {
    return this.http.post<Wallet>(`/api/v2/bank/getBalance`, form);
  }

  postBalance(form: WalletAdd): Observable<Wallet> {
    return this.http.post<Wallet>(`/api/v2/bank/addBalance`, form);
  }

  removeBalance(form: WalletRemove): Observable<Wallet> {
    return this.http.post<Wallet>(`/api/v2/bank/removeBalance`, form);
  }

}

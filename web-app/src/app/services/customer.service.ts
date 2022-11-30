import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Customer } from "../models/customer";
import { PageResult } from "../models/result";


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(
    private readonly http: HttpClient
  ) {}

  getAll(): Observable<PageResult<Customer>> {
    return this.http.get<PageResult<Customer>>(`/api/v1/customer`);
  }

  getCustomerById(id: string) {
    return this.http.get<Customer>(`/api/v1/customer/${id}`);
  }

}

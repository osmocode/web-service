import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Rent, RentForm } from "../models/rent";


@Injectable({
  providedIn: 'root'
})
export class RentalService {


  constructor(
    private readonly http: HttpClient
  ) { }

  getRentById(id: string): Observable<Rent> {
    return this.http.get<Rent>(`/api/v1/rent/${id}`);
  }

  postRent(form: RentForm): Observable<Rent> {
    return this.http.post<Rent>(`/api/v1/rent`, {
      start: form.start,
      end: form.end,
      bike: form.bike.id
    });
  }
}

import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Bike, BikeForm } from "../models/bike";
import { PageResult } from "../models/result";


@Injectable({
  providedIn: 'root'
})
export class BikeService {

  constructor(
    private readonly http: HttpClient
  ) { }

  getAll(): Observable<PageResult<Bike>> {
    return this.http.get<PageResult<Bike>>(`/api/v1/bike`);
  }

  getBikeById(id: string) {
    return this.http.get<Bike>(`/api/v1/bike/${id}`);
  }

  post(form: BikeForm): Observable<Bike> {
    return this.http.post<Bike>(`/api/v1/bike`, form);
  }

}

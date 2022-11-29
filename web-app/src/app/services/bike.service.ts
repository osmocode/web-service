import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Bike } from "../models/bike";
import { PageResult } from "../models/result";


@Injectable({
  providedIn: 'root'
})
export class BikeService {


  constructor(
    private readonly http: HttpClient
  ) { }

  getAll(): Observable<PageResult<Bike>> {
    return this.http.get<PageResult<Bike>>(`http://localhost:8080/bike`);
  }

  getBikeById(id: string) {
    return this.http.get<Bike>(`localhost:8080/bike/${id}`);
  }

}

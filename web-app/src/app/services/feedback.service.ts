import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Feedback, FeedbackForm } from "../models/feedback";


@Injectable({
  providedIn: 'root'
})
export class FeedbackService {


  constructor(
    private readonly http: HttpClient
  ) { }

  getFeedbackById(id: string): Observable<Feedback> {
    return this.http.get<Feedback>(`/api/v1/feedback/${id}`);
  }

  postFeedback(id: string, form: FeedbackForm): Observable<Feedback> {
    return this.http.post<Feedback>(`/api/v1/feedback/${id}`, form);
  }

}

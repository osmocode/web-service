import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Article, ArticleForm } from "../models/sell";


@Injectable({
  providedIn: 'root'
})
export class SellService {

  constructor(
    private readonly http: HttpClient
  ) { }

  postArticle(form: ArticleForm): Observable<Article> {
    return this.http.post<Article>(`/api/v2/article`, form);
  }

}

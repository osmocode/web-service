import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Article, ArticlesGet, ArticlePost, ArticleGet, BasketGet, BasketPost, ArticleList } from "../models/sell";


@Injectable({
  providedIn: 'root'
})
export class SellService {

  constructor(
    private readonly http: HttpClient
  ) { }

  getArticleList(form: ArticlesGet): Observable<ArticleList> {
    return this.http.post<ArticleList>(`/api/v2/sell/getArticleList`, form);
  }

  getArticle(form: ArticleGet): Observable<Article> {
    return this.http.post<Article>(`/api/v2/sell/getArticle`, form);
  }

  postArticle(form: ArticlePost): Observable<Article> {
    return this.http.post<Article>(`/api/v2/sell/postArticle`, form);
  }

  getBasket(form: BasketGet): Observable<ArticleList> {
    return this.http.post<ArticleList>(`/api/v2/sell/getBasket`, form);
  }

  postBasket(form: BasketPost): Observable<Article> {
    return this.http.post<Article>(`/api/v2/sell/postBasket`, form);
  }

}

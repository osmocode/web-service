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
    return this.http.post<ArticleList>(`/api/v2/getArticleList`, form);
  }

  getArticle(form: ArticleGet): Observable<Article> {
    return this.http.post<Article>(`/api/v2/getArticle`, form);
  }

  postArticle(form: ArticlePost): Observable<Article> {
    return this.http.post<Article>(`/api/v2/postArticle`, form);
  }

  getBasket(form: BasketGet): Observable<ArticleList> {
    return this.http.post<ArticleList>(`/api/v2/getBasket`, form);
  }

  postBasket(form: BasketPost): Observable<Article> {
    return this.http.post<Article>(`/api/v2/postBasket`, form);
  }

}

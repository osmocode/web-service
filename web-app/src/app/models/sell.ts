export interface ArticleList {
  articles: Article[];
}

export interface Article {
  uuid: string;
  label: string;
  description: string;
  owner: string;
  state: string;
  price: number;
  currency: string;
  available: boolean;
}

export interface ArticlePost {
  token: string;
  uuid: string;
  price: number;
  currency: string;
}

export interface ArticlesGet {
  token: string;
  currency: string;
}

export interface ArticleGet {
  token: string;
  uuid: string;
  currency: string;
}

export interface BasketGet {
  token: string;
  currency: string;
}

export interface BasketPost {
  token: string;
  bikeUuid: string;
  currency: string;
}

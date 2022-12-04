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

export interface ArticleForm {
  token: string;
  uuid: string;
  price: number;
  currency: string;
}

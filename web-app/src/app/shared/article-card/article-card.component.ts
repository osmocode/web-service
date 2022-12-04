import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Article } from "src/app/models/sell";


@Component({
  selector: 'app-article-card',
  templateUrl: './article-card.component.html',
  styleUrls: ['./article-card.component.scss']
})
export class ArticleCardComponent {

  @Input() inCart!: boolean;
  @Input() article?: Article;
  @Output() didClick= new EventEmitter<string>();
  @Output() didClickAddCart= new EventEmitter<string>();
}

import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Article } from "src/app/models/sell";


@Component({
  selector: 'app-article-list',
  templateUrl: './article-list.component.html',
  styleUrls: ['./article-list.component.scss']
})
export class ArticleListComponent {

  @Input() inCart!: boolean;
  @Input() articles?: Article[];
  @Output() didClickItem = new EventEmitter<string>();
  @Output() didClickAddCartItem = new EventEmitter<string>();

}

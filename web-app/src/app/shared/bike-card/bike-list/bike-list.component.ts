import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Bike } from "src/app/models/bike";


@Component({
  selector: 'app-bike-list',
  templateUrl: './bike-list.component.html',
  styleUrls: ['./bike-list.component.scss']
})
export class BikeListComponent {

  @Input() bikes?: Bike[];
  @Output() didClickItem = new EventEmitter<string>();

}

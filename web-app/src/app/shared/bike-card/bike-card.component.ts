import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Bike } from "src/app/models/bike";


@Component({
  selector: 'app-bike-card',
  templateUrl: './bike-card.component.html',
  styleUrls: ['./bike-card.component.scss']
})
export class BikeCardComponent {

  @Input() bike!: Bike;
  @Output() didClick= new EventEmitter<string>();
}

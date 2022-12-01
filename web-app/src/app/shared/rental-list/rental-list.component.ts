import { Component, EventEmitter, Input, Output } from "@angular/core";
import { Rent } from "src/app/models/rent";


@Component({
  selector: 'app-rental-list',
  templateUrl: './rental-list.component.html',
  styleUrls: ['./rental-list.component.scss']
})
export class RentalListComponent {

  @Input() rentals!: Rent[];
  @Output() didClickRent = new EventEmitter<Rent>();

}

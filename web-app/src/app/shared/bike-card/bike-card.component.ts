import { Component, Input } from "@angular/core";


@Component({
  selector: 'app-bike-card',
  templateUrl: './bike-card.component.html',
  styleUrls: ['./bike-card.component.scss']
})
export class BikeCardComponent {

  @Input() label!: string;
  @Input() desc!: string;
  @Input() image!: string;

}

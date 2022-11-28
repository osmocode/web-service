import { Component } from "@angular/core";


@Component({
  selector: 'app-bike-list',
  templateUrl: './bike-list.component.html',
  styleUrls: ['./bike-list.component.scss']
})
export class BikeListComponent {

  bikes: Bike[] = [
    {
      name: 'SuperBike',
      desc: 'Lorem ipsum'
    },
    {
      name: 'SuperBike',
      desc: 'Lorem ipsum'
    },
    {
      name: 'SuperBike',
      desc: 'Lorem ipsum'
    },
    {
      name: 'SuperBike',
      desc: 'Lorem ipsum'
    }
  ]

}

interface Bike {
  name: string;
  desc: string;
}

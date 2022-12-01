import { Bike } from "./bike";

export interface Rent {
  id: string;
  start: string;
  end: string;
  customer: string;
}

export interface RentForm {
  start: Date;
  end: Date;
  bike: Bike;
}

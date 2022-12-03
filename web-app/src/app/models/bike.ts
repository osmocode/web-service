export enum BikeState {
  EXCELLENT = "EXCELLENT",
  VERY_GOOD = "VERY_GOOD",
  GOOD = "GOOD",
  CORRECT = "CORRECT",
  BAD = "BAD",
  VERY_BAD = "VERY_BAD"
}
export interface Bike {
  id: string;
  label: string;
  desc: string;
  owner: string;
  state: BikeState;
  rent_history: string[];
  rent_queue: string[];
}

export interface BikeForm {
  label: string;
  desc: string;
  state: string;
}

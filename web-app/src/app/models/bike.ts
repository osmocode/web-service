import { Feedback } from "./feedback";
import { Rent } from "./rent";

export interface Bike {
  id: string;
  label: string;
  owner: string;
  rent_history: Feedback[];
  rent_queue: Rent[];
}

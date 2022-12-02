import { BikeState } from "./bike";

export interface Feedback {
  id: string;
  date: string;
  note: number;
  comment: string;
  bike_state: BikeState;
}

export interface FeedbackForm {
  note: number;
  comment: string;
  bike_state: BikeState;
}

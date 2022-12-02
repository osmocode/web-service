export declare type CustomerType =
  "Eiffel Bike Corp" |
  "Student" |
  "Employee" |
  "External";

export interface Customer {
  id: string;
  first_name: string;
  last_name: string;
  username: string;
  type: CustomerType;
  bikes: string[];
}

export interface CustomerForm {
  first_name: string;
  last_name: string;
  username: string;
  password: string;
  type: CustomerType;
}

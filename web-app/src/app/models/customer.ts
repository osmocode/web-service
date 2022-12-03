export enum CustomerType {
  STUDENT = "STUDENT",
  EMPLOYEE = "EMPLOYEE",
  EIFFEL_BIKE_CORP = "EIFFEL_BIKE_CORP",
  EXTERNAL = "EXTERNAL",
}

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

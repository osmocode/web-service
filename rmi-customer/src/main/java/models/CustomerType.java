package models;

public enum CustomerType {
    STUDENT, EMPLOYEE, EIFFEL_BIKE_CORP, EXTERNAL;

    public String toString() {
        switch (this) {
            case STUDENT:
                return "Student";

            case EMPLOYEE:
                return "Employee";

            case EIFFEL_BIKE_CORP:
                return "Eiffel Bike Corp";

            case EXTERNAL:
                return "External";

            default:
                return "null";
        }
    }

    public boolean canProposeBike() {
        switch (this) {
            case STUDENT:

            case EMPLOYEE:

            case EIFFEL_BIKE_CORP:
                return true;

            default:
                return false;
        }
    }

    public boolean canRent() {
        switch (this) {
            case STUDENT:

            case EMPLOYEE:
                return true;

            default:
                return false;
        }
    }
}

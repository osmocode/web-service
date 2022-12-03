package rmi.customer.models;

public enum CustomerType {
    STUDENT, EMPLOYEE, EIFFEL_BIKE_CORP, EXTERNAL;

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

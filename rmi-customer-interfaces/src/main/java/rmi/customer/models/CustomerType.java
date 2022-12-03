package rmi.customer.models;

public enum CustomerType {
    STUDENT, EMPLOYEE, EIFFEL_BIKE_CORP, EXTERNAL;

    public boolean canProposeBike() {
        return switch (this) {
            case STUDENT, EMPLOYEE, EIFFEL_BIKE_CORP -> true;
            default -> false;
        };
    }

    public boolean canRent() {
        return switch (this) {
            case STUDENT, EMPLOYEE -> true;
            default -> false;
        };
    }
}

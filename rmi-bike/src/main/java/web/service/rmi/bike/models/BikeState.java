package web.service.rmi.bike.models;

public enum BikeState {
EXCELLENT, VERY_GOOD, GOOD, CORRECT, BAD, VERY_BAD;

    public String toString() {
        switch (this) {
            case EXCELLENT:
                return "Exellent";

            case VERY_GOOD:
                return "Very good";

            case GOOD:
                return "Good";

            case CORRECT:
                return "Correct";

            case BAD:
                return "Bad";

            case VERY_BAD:
                return "Very bad";

            default:
                return "null";
        }
    }
}

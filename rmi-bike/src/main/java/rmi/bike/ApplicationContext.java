package rmi.bike;

import rmi.bike.models.BikeState;
import rmi.bike.models.bike.BikeList;
import rmi.bike.models.feedback.FeedbackList;
import rmi.bike.models.rent.RentList;
import rmi.customer.interfaces.CustomerListService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ApplicationContext {
    private final boolean DEMO = true;

    private final BikeList bikes;
    private final RentList rents;
    private final FeedbackList feedbacks;

    private final String hostBikes;
    private final String hostRental;
    private final String hostFeedbacks;
    private final String hostCustomers;

    private CustomerListService customers;

    public ApplicationContext() throws RemoteException {
        bikes = new BikeList(this);
        rents = new RentList(this);
        feedbacks = new FeedbackList(this);

        hostBikes = System.getenv().getOrDefault("BIKE_SERVICE_HOST", "localhost:1099/BikeListService");
        hostRental = System.getenv().getOrDefault("LOCATION_SERVICE_HOST", "localhost:1099/RentListService");
        hostFeedbacks = System.getenv().getOrDefault("FEEDBACK_SERVICE_HOST", "localhost:1099/FeedbackListService");
        hostCustomers = System.getenv().getOrDefault("CUSTOMER_SERVICE_HOST", "localhost:1098/CustomerListService");
    }

    public BikeList getBikes() {
        return bikes;
    }

    public RentList getRents() {
        return rents;
    }

    public FeedbackList getFeedbacks() {
        return feedbacks;
    }

    public CustomerListService getCustomers() { return customers; }

    public void runServer() throws IOException, ParseException {
        Objects.requireNonNull(customers);

        Naming.rebind("rmi://" + hostBikes, bikes);
        System.out.println("BikeList Service was bind successfully...");

        Naming.rebind("rmi://" + hostRental, rents);
        System.out.println("RentList Service was bind successfully...");

        Naming.rebind("rmi://" + hostFeedbacks, feedbacks);
        System.out.println("FeedbackList Service was bind successfully...");

        if (DEMO) {
            addAllDemo();
        }
    }

    public void connectServer() throws MalformedURLException, NotBoundException, RemoteException {
        customers = (CustomerListService) Naming.lookup("rmi://" + hostCustomers);
    }

    public static void main(String[] args) throws IOException, ParseException, NotBoundException {
        var applicationContext = new ApplicationContext();

        LocateRegistry.createRegistry(1099);

        applicationContext.connectServer();
        applicationContext.runServer();
    }

    private void addAllDemo() throws IOException, ParseException {
        addBikesDemo();
        addRentDemo();
        addFeedbackDemo();
    }

    private void addBikesDemo() throws IOException {
        // TODO change UUID.randomUUID() to the UUID of the owner customer.

        bikes.add("bike1", UUID.randomUUID(), BikeState.EXCELLENT);
        bikes.add("bike2", UUID.randomUUID(), BikeState.CORRECT);
        bikes.add("bike3", UUID.randomUUID(), BikeState.VERY_GOOD);
        bikes.add("bike4", UUID.randomUUID(), BikeState.BAD);
        bikes.add("bike5", UUID.randomUUID(), BikeState.GOOD);
        bikes.add("bike6", UUID.randomUUID(), BikeState.VERY_BAD);
        bikes.add("bike7", UUID.randomUUID(), BikeState.CORRECT);
    }

    private void addRentDemo() throws RemoteException, ParseException {
        // TODO
        // change the 1st UUID.randomUUID() by the UUID of the client customer.
        // change the 2nd UUID.randomUUID() by the UUID of the bike.

        rents.add(parseDate("25/04/2022"), parseDate("27/04/2022"), UUID.randomUUID(), UUID.randomUUID());
        rents.add(parseDate("22/03/2022"), parseDate("05/04/2022"), UUID.randomUUID(), UUID.randomUUID());
        rents.add(parseDate("29/04/2022"), parseDate("7/04/2022"), UUID.randomUUID(), UUID.randomUUID());
        rents.add(parseDate("25/05/2022"), parseDate("26/05/2022"), UUID.randomUUID(), UUID.randomUUID());
        rents.add(parseDate("12/04/2022"), parseDate("15/04/2022"), UUID.randomUUID(), UUID.randomUUID());
        rents.add(parseDate("25/04/2022"), parseDate("27/05/2022"), UUID.randomUUID(), UUID.randomUUID());
        rents.add(parseDate("08/04/2022"), parseDate("13/04/2022"), UUID.randomUUID(), UUID.randomUUID());
        rents.add(parseDate("20/04/2022"), parseDate("30/04/2022"), UUID.randomUUID(), UUID.randomUUID());
    }

    private void addFeedbackDemo() throws ParseException, RemoteException {
        // TODO change the 2nd UUID.randomUUID() by the UUID of the rent.

        feedbacks.add(parseDate("18/04/2022"), 3, "comment 1", BikeState.BAD, UUID.randomUUID());
        feedbacks.add(parseDate("8/04/2022"), -1, null, null, UUID.randomUUID());
        feedbacks.add(parseDate("19/05/2022"), -1, null, BikeState.VERY_BAD, UUID.randomUUID());
        feedbacks.add(parseDate("01/04/2022"), 2, "comment 2", null, UUID.randomUUID());
        feedbacks.add(parseDate("13/05/2022"), 3, "comment 3", BikeState.VERY_GOOD, UUID.randomUUID());
        feedbacks.add(parseDate("14/06/2022"), 1, null, null, UUID.randomUUID());
        feedbacks.add(parseDate("08/04/2022"), 0, "comment 4", BikeState.GOOD, UUID.randomUUID());
        feedbacks.add(parseDate("17/04/2022"), 2, null, BikeState.CORRECT, UUID.randomUUID());
        feedbacks.add(parseDate("20/04/2022"), 5, "comment 5", null, UUID.randomUUID());
    }

    private Date parseDate(String date) throws ParseException {
        var formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.parse(Objects.requireNonNull(date));
    }
}

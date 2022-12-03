package rmi.bike;

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

public class ApplicationContext {
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

    public void runServer() throws IOException {
        Objects.requireNonNull(customers);

        Naming.rebind("rmi://" + hostBikes, bikes);
        System.out.println("BikeList Service was bind successfully...");

        Naming.rebind("rmi://" + hostRental, rents);
        System.out.println("RentList Service was bind successfully...");

        Naming.rebind("rmi://" + hostFeedbacks, feedbacks);
        System.out.println("FeedbackList Service was bind successfully...");
    }

    public void connectServer() throws MalformedURLException, NotBoundException, RemoteException {
        customers = (CustomerListService) Naming.lookup("rmi://" + hostCustomers);
    }

    public static void main(String[] args) throws IOException, NotBoundException {
        var applicationContext = new ApplicationContext();

        LocateRegistry.createRegistry(1099);

        applicationContext.connectServer();
        applicationContext.runServer();
    }

    public Date parseDate(String date) throws ParseException {
        var formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.parse(Objects.requireNonNull(date));
    }
}

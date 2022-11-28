import rmi.bike.models.bike.BikeList;
import rmi.bike.models.feedback.FeedbackList;
import rmi.bike.models.rent.RentList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class BikeService {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);

        var bikeMap = new BikeList();
        var rentMap = new RentList();
        var feedbackMap = new FeedbackList();

        addAllTest(bikeMap, rentMap, feedbackMap); // Optional Line

        Naming.rebind("rmi://localhost:1099/BikeListService", bikeMap);
        System.out.println("BikeList Service was bind successfully...");

        Naming.rebind("rmi://localhost:1099/RentListService", rentMap);
        System.out.println("RentList Service was bind successfully...");

        Naming.rebind("rmi://localhost:1099/FeedbackListService", feedbackMap);
        System.out.println("FeedbackList Service was bind successfully...");
    }

    private static void addAllTest(BikeList bikeList, RentList rentList, FeedbackList feedbackList) throws RemoteException {
        if (bikeList != null) {
            addBikeTest(bikeList);
        }
        if (rentList != null) {
            addRentTest(rentList);
        }
        if (feedbackList != null) {
            addFeedbackTest(feedbackList);
        }
    }

    private static void addBikeTest(BikeList bikeList) throws RemoteException {
        // TODO

        /*
        bikeList.add();
        bikeList.add();
        bikeList.add();
        */
    }

    private static void addRentTest(RentList rentList) throws RemoteException {
        // TODO

        /*
        rentList.add();
        rentList.add();
        rentList.add();
         */
    }

    private static void addFeedbackTest(FeedbackList feedbackList) throws RemoteException {
        // TODO

        /*
        feedbackList.add();
        feedbackList.add();
        feedbackList.add();
         */
    }
}

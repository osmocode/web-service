package rmi.bike.models.feedback;


import rmi.bike.ApplicationContext;
import rmi.bike.interfaces.feedback.FeedbackListService;
import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.models.BikeState;
import rmi.bike.models.bike.Bike;
import rmi.bike.models.rent.Rent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class FeedbackList extends UnicastRemoteObject implements FeedbackListService {
    private final ApplicationContext context;
    private final Map<UUID, Feedback> feedbacks = new HashMap<>();

    public FeedbackList(ApplicationContext context) throws RemoteException {
        super();
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public Map<UUID, ? extends FeedbackService> getAll() throws RemoteException {
        return feedbacks;
    }

    @Override
    public FeedbackService getFeedbackByUUID(String uuid) throws RemoteException {
        return feedbacks.get(UUID.fromString(Objects.requireNonNull(uuid)));
    }

    @Override
    public FeedbackService add(Date date, int note, String comment, BikeState bikeState, UUID rentUUID) throws RemoteException {
        Feedback feedback;
        var rent = (Rent) context.getRents().getRentByUUID(rentUUID.toString());
        if (rent == null) {
            return null;
        }

        try {
            feedback = new Feedback(date, note, comment, bikeState, rent);
        } catch (NullPointerException e) {
            return null;
        }
        // Add in feedbacks
        if (feedbacks.putIfAbsent(rentUUID, feedback) != null) {
            return null;
        }

        // Add in Bike.feedbackHistory
        var bike = (Bike) rent.getBike();
        if (bike == null) {
            return null;
        }

        if (!bike.addFeedbackHistory(rentUUID)) {
            return null;
        }
        // Remove rent in Bike.rentQueue
        try {
            bike.removeRentQueue();
        } catch (InterruptedException e) {
            return null;
        }
        return feedback;
    }

    @Override
    public String toString() {
        return "FeedbackList{" +
                "context=" + context +
                ", feedbacks=" + feedbacks +
                '}';
    }
}

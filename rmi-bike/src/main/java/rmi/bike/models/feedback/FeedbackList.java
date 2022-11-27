package rmi.bike.models.feedback;


import rmi.bike.interfaces.feedback.FeedbackListService;
import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;
import rmi.bike.models.rent.Rent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class FeedbackList extends UnicastRemoteObject implements FeedbackListService {
    private final Map<UUID, Feedback> feedbackMap = new HashMap<>();

    public FeedbackList() throws RemoteException {
        super();
    }

    @Override
    public Map<UUID, ? extends FeedbackService> getAll() throws RemoteException {
        return feedbackMap;
    }

    @Override
    public Optional<FeedbackService> getFeedbackByUUID(String uuid) throws RemoteException {
        return Optional.ofNullable(feedbackMap.get(Objects.requireNonNull(uuid)));
    }

    @Override
    public void add(Date date, int note, String comment, BikeState bikeState, UUID rentUUID) throws RemoteException {
        Objects.requireNonNull(date);
        Objects.requireNonNull(comment);
        Objects.requireNonNull(bikeState);
        Objects.requireNonNull(rentUUID);

        if (note > 5 || note < -1) {
            throw new IllegalArgumentException("-1 <= note <= 5");
        }

        UUID uuid;

        // TODO get rent
        //Rent rent = ;

        /*
        do {
            uuid = UUID.randomUUID();
        } while (feedbackMap.putIfAbsent(uuid, new Feedback(date, note, comment, bikeState, )) != null);
         */

        // TODO add uuid in Bike.feedbackHistory
    }
}

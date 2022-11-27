package rmi.bike.models.feedback;


import rmi.bike.interfaces.feedback.FeedbackListService;
import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class FeedbackList extends UnicastRemoteObject implements FeedbackListService {
    private final Map<Long, Feedback> feedbackMap = new HashMap<>();

    public FeedbackList() throws RemoteException {
        super();
    }

    @Override
    public Optional<FeedbackService> getFeedbackByRent(RentService rent) throws RemoteException {
        // TODO
        return Optional.ofNullable(null);
    }

    @Override
    public void add(Date date, int note, String comment, BikeState bikeState, RentService rentService) throws RemoteException {
        Objects.requireNonNull(date);
        Objects.requireNonNull(comment);
        Objects.requireNonNull(bikeState);
        Objects.requireNonNull(rentService);

        if (note > 5 || note < -1) {
            throw new IllegalArgumentException("-1 <= note <= 5");
        }
        //TODO
    }
}

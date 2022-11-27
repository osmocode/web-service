package web.service.rmi.bike.models.feedback;

import web.service.rmi.bike.interfaces.feedback.FeedbackInterface;
import web.service.rmi.bike.interfaces.feedback.FeedbackListInterface;
import web.service.rmi.bike.interfaces.location.LocationInterface;
import web.service.rmi.bike.models.BikeState;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class FeedbackList extends UnicastRemoteObject implements FeedbackListInterface {
    private final Map<Long, Feedback> feedbackMap = new HashMap<>();

    public FeedbackList() throws RemoteException {
        super();
    }

    @Override
    public Optional<FeedbackInterface> getFeedbackByLocation(LocationInterface location) throws RemoteException {
        // TODO
        return Optional.ofNullable(null);
    }

    @Override
    public void add(Date date, int note, String comment, BikeState bikeState, LocationInterface location) throws RemoteException {
        Objects.requireNonNull(date);
        Objects.requireNonNull(comment);
        Objects.requireNonNull(bikeState);
        Objects.requireNonNull(location);

        if (note > 5 || note < -1) {
            throw new IllegalArgumentException("-1 <= note <= 5");
        }
        //TODO
    }
}

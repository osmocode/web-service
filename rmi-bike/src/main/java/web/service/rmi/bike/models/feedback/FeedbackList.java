package web.service.rmi.bike.models.feedback;

import web.service.rmi.bike.interfaces.feedback.FeedbackInterface;
import web.service.rmi.bike.interfaces.feedback.FeedbackListInterface;
import web.service.rmi.bike.models.location.Location;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Optional;

public class FeedbackList extends UnicastRemoteObject implements FeedbackListInterface {
    private final Map<Long, Feedback> feedbackMap;

    public FeedbackList(Map<Long, Feedback> feedbackMap) throws RemoteException {
        super();
        this.feedbackMap = Map.copyOf(feedbackMap);
    }

    @Override
    public Optional<FeedbackInterface> getFeedbackByLocation(Location location) throws RemoteException {
        // TODO
        return Optional.ofNullable(null);
    }
}

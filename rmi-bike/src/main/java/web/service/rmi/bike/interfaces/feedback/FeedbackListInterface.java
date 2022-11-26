package web.service.rmi.bike.interfaces.feedback;

import web.service.rmi.bike.models.feedback.Feedback;
import web.service.rmi.bike.models.location.Location;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

public interface FeedbackListInterface extends Remote {
    Optional<FeedbackInterface> getFeedbackByLocation(Location location) throws RemoteException;
}

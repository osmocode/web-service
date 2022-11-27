package web.service.rmi.bike.interfaces.feedback;

import web.service.rmi.bike.interfaces.location.LocationInterface;
import web.service.rmi.bike.models.BikeState;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Optional;

public interface FeedbackListInterface extends Remote {
    Optional<FeedbackInterface> getFeedbackByLocation(LocationInterface location) throws RemoteException;
    void add(Date date, int note, String comment, BikeState bikeState, LocationInterface location) throws RemoteException;
}

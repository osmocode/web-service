package rmi.bike.interfaces.feedback;


import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Optional;

public interface FeedbackListService extends Remote {
    Optional<FeedbackService> getFeedbackByRent(RentService rentService) throws RemoteException;
    void add(Date date, int note, String comment, BikeState bikeState, RentService rentService) throws RemoteException;
}

package rmi.bike.interfaces.bike;

import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;

import java.awt.*;
import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface BikeService extends Remote {
    Image getImage() throws RemoteException;
    UUID getOwnerId() throws RemoteException;
    BikeState getBikeState() throws RemoteException;
    List<? extends FeedbackService> getFeedbackHistory() throws RemoteException;
    List<? extends RentService> getRentQueue() throws RemoteException;
    float getAverageNote() throws RemoteException;
}

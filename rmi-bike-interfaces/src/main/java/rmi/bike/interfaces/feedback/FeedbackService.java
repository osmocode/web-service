package rmi.bike.interfaces.feedback;

import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface FeedbackService extends Remote {
    Date getDate() throws RemoteException;
    int getNote() throws RemoteException;
    String getComment() throws RemoteException;
    BikeState getBikeState() throws RemoteException;
    RentService getRent() throws RemoteException;
}

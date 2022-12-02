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
    String getLabel() throws RemoteException;
    String getDescription() throws RemoteException;
    Image getImage() throws RemoteException;
    void setImage(Image image) throws RemoteException;
    UUID getOwnerId() throws RemoteException;
    void changeOwner(String ownerId) throws RemoteException;
    BikeState getBikeState() throws RemoteException;
    long getPrice() throws RemoteException;
    List<? extends FeedbackService> getFeedbackHistory() throws RemoteException;
    List<UUID> getRentQueue() throws RemoteException;
    float getAverageNote() throws RemoteException;
    boolean canBeRent() throws RemoteException;
    boolean canBeSale() throws RemoteException;
    boolean putToSale(int price) throws RemoteException;
}

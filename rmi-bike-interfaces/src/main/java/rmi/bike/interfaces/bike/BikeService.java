package rmi.bike.interfaces.bike;

import rmi.bike.models.BikeState;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface BikeService extends Remote {
    String getLabel() throws RemoteException;
    String getDescription() throws RemoteException;
    Image getImage() throws RemoteException;
    void setImage(Image image) throws RemoteException;
    UUID getOwnerId() throws RemoteException;
    BikeState getBikeState() throws RemoteException;
    List<UUID> getFeedbackHistory() throws RemoteException;
    List<UUID> getRentQueue() throws RemoteException;
    float getAverageNote() throws RemoteException;
    boolean canBeRent() throws RemoteException;
    boolean canBeSale() throws RemoteException;
}

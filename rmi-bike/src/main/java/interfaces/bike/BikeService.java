package interfaces.bike;

import models.location.Rent;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public interface BikeService extends Remote {
    String getName() throws RemoteException;
    Image getImage() throws RemoteException;
    UUID getOwnerId() throws RemoteException;
    ArrayList<Rent> getRentHistory() throws RemoteException;
    ArrayBlockingQueue<Rent> getRentQueue() throws RemoteException;
}

package rmi.bike.interfaces.bike;

import rmi.bike.interfaces.rent.RentService;

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
    ArrayList<? extends RentService> getRentHistory() throws RemoteException;
    ArrayBlockingQueue<? extends RentService> getRentQueue() throws RemoteException;
}

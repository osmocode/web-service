package web.service.rmi.bike.interfaces.bike;

import web.service.rmi.bike.models.location.Location;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public interface BikeInterface extends Remote {
    String getName() throws RemoteException;
    Image getImage() throws RemoteException;
    UUID getOwnerId() throws RemoteException;
    ArrayList<Location> getLocationHistory() throws RemoteException;
    ArrayBlockingQueue<Location> getLocationQueue() throws RemoteException;
}

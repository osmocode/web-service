package interfaces.bike;

import models.bike.Bike;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface BikeListService extends Remote {
    List<BikeService> getBikesList() throws RemoteException;
    Optional<Bike> getBikeById(String uuid) throws RemoteException;
    Optional<List<BikeService>> getBikeByOwner(String uuid) throws RemoteException;
}

package web.service.rmi.bike.interfaces.bike;

import web.service.rmi.bike.models.bike.Bike;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface BikeListInterface extends Remote {
    List<BikeInterface> getBikesList() throws RemoteException;
    Optional<Bike> getBikeById(String uuid) throws RemoteException;
    Optional<List<BikeInterface>> getBikeByOwner(String uuid) throws RemoteException;
}

package web.service.rmi.bike.interfaces.bike;

import web.service.rmi.bike.models.bike.Bike;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface BikeListInterface extends Remote {

    Optional<Bike> getBikeById(long id) throws RemoteException;

    Optional<List<BikeInterface>> getBikeByOwner(long id) throws RemoteException;

    List<BikeInterface> getBikesList() throws RemoteException;

}

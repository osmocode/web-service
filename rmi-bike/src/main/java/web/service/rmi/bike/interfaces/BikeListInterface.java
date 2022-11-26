package web.service.rmi.bike.interfaces;

import web.service.rmi.bike.models.Bike;
import web.service.rmi.bike.models.BikeList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface BikeListInterface extends Remote {

    Optional<Bike> getBikeById(long id) throws RemoteException;

    Optional<List<Bike>> getBikeByOwner(long id) throws RemoteException;

}

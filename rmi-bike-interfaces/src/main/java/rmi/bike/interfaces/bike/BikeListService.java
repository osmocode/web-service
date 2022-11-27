package rmi.bike.interfaces.bike;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface BikeListService extends Remote {
    List<BikeService> getBikesList() throws RemoteException;
    Optional<BikeService> getBikeById(String uuid) throws RemoteException;
    Optional<List<BikeService>> getBikeByOwner(String uuid) throws RemoteException;
}

package web.service.rmi.bike.models;

import web.service.rmi.bike.interfaces.BikeListInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class BikeList extends UnicastRemoteObject implements BikeListInterface {

    private final HashMap<Long, Bike> bikes = new HashMap<>();

    public BikeList() throws RemoteException {
        super();
    }

    @Override
    public Optional<Bike> getBikeById(long id) throws RemoteException {
        return Optional.ofNullable(this.bikes.get(id));
    }

    @Override
    public Optional<List<Bike>> getBikeByOwner(long id) throws RemoteException {

        return Optional.empty();
    }
}
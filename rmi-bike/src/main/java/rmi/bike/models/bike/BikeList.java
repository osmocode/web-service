package rmi.bike.models.bike;

import rmi.bike.interfaces.bike.BikeListService;
import rmi.bike.interfaces.bike.BikeService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

public class BikeList extends UnicastRemoteObject implements BikeListService {

    private final HashMap<UUID, Bike> bikeMap = new HashMap<>();

    public BikeList() throws RemoteException {
        super();
    }

    @Override
    public Optional<BikeService> getBikeById(String uuid) throws RemoteException {
        return Optional.ofNullable(bikeMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }

    @Override
    public Optional<List<BikeService>> getBikeByOwner(String uuid) throws RemoteException {
        // TODO
        return Optional.ofNullable(null);
    }

    @Override
    public List<BikeService> getBikesList() {
        return bikeMap.values().stream().collect(Collectors.toList());
    }
}
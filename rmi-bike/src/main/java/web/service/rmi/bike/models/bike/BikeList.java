package web.service.rmi.bike.models.bike;

import web.service.rmi.bike.interfaces.bike.BikeInterface;
import web.service.rmi.bike.interfaces.bike.BikeListInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.stream.Collectors;

public class BikeList extends UnicastRemoteObject implements BikeListInterface {

    private final HashMap<UUID, Bike> bikeMap = new HashMap<>();

    public BikeList() throws RemoteException {
        super();
        this.bikeMap.put(1L, new Bike("Toto", null, null, null));
        this.bikeMap.put(2L, new Bike("Titi", null, null, null));
        this.bikeMap.put(3L, new Bike("Tata", null, null, null));
    }

    @Override
    public Optional<Bike> getBikeById(String uuid) throws RemoteException {
        return Optional.ofNullable(bikeMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }

    @Override
    public Optional<List<BikeInterface>> getBikeByOwner(String uuid) throws RemoteException {
        // TODO
        return Optional.ofNullable(null);
    }

    @Override
    public List<BikeInterface> getBikesList() {
        return bikeMap.values().stream().collect(Collectors.toList());
    }
}
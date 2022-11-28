package rmi.bike.models.bike;

import rmi.bike.interfaces.bike.BikeListService;
import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.models.BikeState;

import java.awt.*;
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
    public Map<UUID, ? extends BikeService> getAll() {
        return bikeMap;
    }

    @Override
    public Optional<BikeService> getBikeByUUID(String uuid) throws RemoteException {
        return Optional.ofNullable(bikeMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }

    @Override
    public void add(Image image, UUID ownerUUID, BikeState bikeState) throws RemoteException {
        //Objects.requireNonNull(image);
        //Objects.requireNonNull(ownerUUID);
        //Objects.requireNonNull(bikeState);

        UUID uuid;

        do {
            uuid = UUID.randomUUID();
        } while (bikeMap.putIfAbsent(uuid, new Bike(image, ownerUUID, bikeState)) != null);

        // TODO add uuid in Customer.bikes
    }
}
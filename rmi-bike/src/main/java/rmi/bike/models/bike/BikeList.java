package rmi.bike.models.bike;

import rmi.bike.ApplicationContext;
import rmi.bike.interfaces.bike.BikeListService;
import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.models.BikeState;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class BikeList extends UnicastRemoteObject implements BikeListService {
    private final ApplicationContext context;
    private final HashMap<UUID, Bike> bikes = new HashMap<>();

    public BikeList(ApplicationContext applicationContext) throws RemoteException {
        super();
        this.context = Objects.requireNonNull(applicationContext);
    }

    @Override
    public Map<UUID, ? extends BikeService> getAll() {
        return bikes;
    }

    @Override
    public BikeService getBikeByUUID(String uuid) throws RemoteException {
        return bikes.get(UUID.fromString(Objects.requireNonNull(uuid)));
    }

    @Override
    public Map<UUID, ? extends BikeService> add(String label, String desc, UUID ownerUUID, BikeState bikeState) throws RemoteException {
        UUID uuid;
        Bike bike;

        try {
            bike = new Bike(label, desc, ownerUUID, bikeState);
        } catch (NullPointerException e) {
            return null;
        }

        // Add bike in bikes
        do {
            uuid = UUID.randomUUID();
        }
        while (bikes.putIfAbsent(uuid, bike) != null);

        // Add uuid in Customer.bikes
        var customer = context.getCustomers().getCustomerByUUID(ownerUUID.toString());
        if (customer == null) {
            return null;
        }
        customer.addBike(uuid);
        return Map.of(uuid, bike);
    }

    @Override
    public String toString() {
        return "BikeList{" +
                "context=" + context +
                ", bikes=" + bikes +
                '}';
    }
}
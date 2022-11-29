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

    public BikeList(ApplicationContext applicationContext) throws RemoteException, IOException {
        super();
        this.context = Objects.requireNonNull(applicationContext);

        if (context.DEMO) {
            addBikesDemo();
        }
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
    public Map<UUID, ? extends BikeService> add(String label, UUID ownerUUID, BikeState bikeState) throws RemoteException {
        UUID uuid;
        Bike bike;

        try {
            bike = new Bike(label, ownerUUID, bikeState);
        } catch (NullPointerException e) {
            return null;
        }

        // Add bike in bikes
        do {
            uuid = UUID.randomUUID();
        } while (bikes.putIfAbsent(uuid, bike) != null);

        // Add uuid in Customer.bikes
        var customer = context.getCustomers().getCustomerByUUID(ownerUUID.toString());
        if (customer == null) {
            return null;
        }

        if (!customer.getBikes().add(uuid)) {
            return null;
        }

        return Map.of(uuid, bike);
    }

    @Override
    public String toString() {
        return "BikeList{" +
                "context=" + context +
                ", bikes=" + bikes +
                '}';
    }

    private void addBikesDemo() throws IOException {
        bikes.put(UUID.fromString("00000000-0000-0000-0000-00000000"), new Bike("bike0", UUID.fromString("00000000-0000-0000-0000-00000000"), BikeState.EXCELLENT));
        bikes.put(UUID.fromString("00000000-0000-0000-0000-00000001"), new Bike("bike1", UUID.fromString("00000000-0000-0000-0000-00000000"), BikeState.CORRECT));
        bikes.put(UUID.fromString("00000000-0000-0000-0000-00000002"), new Bike("bike2", UUID.fromString("00000000-0000-0000-0000-00000000"), BikeState.VERY_GOOD));
        bikes.put(UUID.fromString("00000000-0000-0000-0000-00000003"), new Bike("bike3", UUID.fromString("00000000-0000-0000-0000-00000001"), BikeState.BAD));
        bikes.put(UUID.fromString("00000000-0000-0000-0000-00000004"), new Bike("bike4", UUID.fromString("00000000-0000-0000-0000-00000001"), BikeState.GOOD));
        bikes.put(UUID.fromString("00000000-0000-0000-0000-00000005"), new Bike("bike5", UUID.fromString("00000000-0000-0000-0000-00000004"), BikeState.VERY_BAD));
        bikes.put(UUID.fromString("00000000-0000-0000-0000-00000006"), new Bike("bike6", UUID.fromString("00000000-0000-0000-0000-00000005"), BikeState.CORRECT));
    }
}
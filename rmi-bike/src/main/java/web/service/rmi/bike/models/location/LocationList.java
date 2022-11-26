package web.service.rmi.bike.models.location;

import web.service.rmi.bike.interfaces.location.LocationInterface;
import web.service.rmi.bike.interfaces.location.LocationListInterface;
import web.service.rmi.customer.interfaces.customer.CustomerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LocationList extends UnicastRemoteObject implements LocationListInterface {
    private final Map<UUID, List<Location>> locationMap = new ConcurrentHashMap<>();

    public LocationList() throws RemoteException {
        super();
    }

    @Override
    public Optional<List<? extends LocationInterface>> getLocationByCustomer(String uuid) throws RemoteException {
        return Optional.ofNullable(locationMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }

    @Override
    public void addLocation(Date start, Date end, CustomerInterface customerClient) throws RemoteException {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        Objects.requireNonNull(customerClient);

        synchronized (locationMap) {
            var uuid = customerClient.getUUID();
            var location = new Location(start, end, customerClient);

            if (locationMap.get(uuid) == null) {
                locationMap.put(uuid, List.of(location));
            } else {
                locationMap.get(uuid).add(location);
            }
        }
    }
}

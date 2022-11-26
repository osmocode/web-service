package web.service.rmi.bike.models.location;

import web.service.rmi.bike.interfaces.location.LocationInterface;
import web.service.rmi.bike.interfaces.location.LocationListInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class LocationList extends UnicastRemoteObject implements LocationListInterface {
    private final Map<Long, List<Location>> locationMap;

    public LocationList(Map<Long, List<Location>> locationList) throws RemoteException {
        super();
        this.locationMap = Map.copyOf(locationList);
    }

    @Override
    public Optional<List<LocationInterface>> getLocationByCustomer(long id) throws RemoteException {
        // TODO
        return Optional.ofNullable(null);
    }
}

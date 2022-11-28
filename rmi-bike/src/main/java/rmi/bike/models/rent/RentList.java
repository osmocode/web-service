package rmi.bike.models.rent;

import rmi.bike.interfaces.rent.RentListService;
import rmi.bike.interfaces.rent.RentService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RentList extends UnicastRemoteObject implements RentListService {
    private final Map<UUID, Rent> rentMap = new ConcurrentHashMap<>();

    public RentList() throws RemoteException {
        super();
    }

    @Override
    public Map<UUID, ? extends RentService> getAll() throws RemoteException {
        return rentMap;
    }

    @Override
    public Optional<RentService> getRentByUUID(String uuid) throws RemoteException {
        return Optional.ofNullable(rentMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }

    @Override
    public void add(Date start, Date end, UUID customerClientUUID) throws RemoteException {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        Objects.requireNonNull(customerClientUUID);

        // TOOD add in mapRent
        /*
        synchronized (rentMap) {
            var uuid = customerClient.getUUID();
            var rent = new Rent(start, end, customerClient);


            if (rentMap.get(uuid) == null) {
                rentMap.put(uuid, List.of(rent));
            } else {
                rentMap.get(uuid).add(rent);
            }
        }
         */

        // TODO add uuid into Bike.rentQueue
    }
}

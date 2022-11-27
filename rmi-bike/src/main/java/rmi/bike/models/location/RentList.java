package rmi.bike.models.location;

import rmi.bike.interfaces.rent.RentListService;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RentList extends UnicastRemoteObject implements RentListService {
    //private final Map<UUID, List<Rent>> rentMap = new ConcurrentHashMap<>();
    private final Map<UUID, Rent> rentMap = new ConcurrentHashMap<>();

    public RentList() throws RemoteException {
        super();
    }

    /*
    @Override
    public Optional<List<? extends RentService>> getRentByCustomer(String uuid) throws RemoteException {
        return Optional.ofNullable(rentMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }
    */

    @Override
    public void add(Date start, Date end, UUID customerClientUUID) throws RemoteException {
        Objects.requireNonNull(start);
        Objects.requireNonNull(end);
        Objects.requireNonNull(customerClientUUID);

        // TOOD
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
    }
}

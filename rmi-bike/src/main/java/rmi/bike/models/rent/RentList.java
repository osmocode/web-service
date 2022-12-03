package rmi.bike.models.rent;

import rmi.bike.ApplicationContext;
import rmi.bike.interfaces.rent.RentListService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.bike.Bike;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RentList extends UnicastRemoteObject implements RentListService {
    private final ApplicationContext context;
    private final Map<UUID, Rent> rents = new ConcurrentHashMap<>();

    public RentList(ApplicationContext context) throws RemoteException {
        super();
        this.context = Objects.requireNonNull(context);
    }

    @Override
    public Map<UUID, ? extends RentService> getAll() throws RemoteException {
        return rents;
    }

    @Override
    public RentService getRentByUUID(String uuid) throws RemoteException {
        return rents.get(UUID.fromString(Objects.requireNonNull(uuid)));
    }

    public long getNumberOfOnGoingRents() throws RemoteException{
        var date = new Date();
        return rents.entrySet().stream().filter(t -> {
            try {
                return t.getValue().getEnd().getTime() > date.getTime() && t.getValue().getStart().getTime() <= date.getTime() ;
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        }).count();
    }

    public long getNumberOfRentsPlanned() throws RemoteException{
        var date = new Date();
        return rents.entrySet().stream().filter(t -> {
            try {
                return t.getValue().getStart().getTime() > date.getTime();
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return false;
            }
        }).count();
    }

    @Override
    public Map<UUID, ? extends RentService> add(Date start, Date end, UUID customerClientUUID, UUID bikeUUID) throws RemoteException {
        UUID uuid;
        Rent rent;

        var bike = (Bike) context.getBikes().getBikeByUUID(bikeUUID.toString());
        if (bike == null) {
            return null;
        }

        try {
            rent = new Rent(start, end, customerClientUUID, bike);
        } catch (NullPointerException e) {
            return null;
        }

        // Add in rents
        do {
            uuid = UUID.randomUUID();
        } while (rents.putIfAbsent(uuid, rent) != null);

        // Add in Bike.rentQueue
        try {
            bike.addRentQueue(uuid);
        } catch (InterruptedException e) {
            return null;
        }

        return Map.of(uuid, rent);
    }

    @Override
    public String toString() {
        return "RentList{" +
                "context=" + context +
                ", rents=" + rents +
                '}';
    }
}

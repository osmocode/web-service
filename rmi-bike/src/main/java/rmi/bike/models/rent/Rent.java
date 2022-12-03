package rmi.bike.models.rent;


import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.bike.Bike;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Rent extends UnicastRemoteObject implements RentService {
    private final Date start;
    private final Date end;
    private final UUID customerClientUUID;
    private final Bike bike;

    public Rent(Date start, Date end, UUID customerClientUUID, Bike bike) throws RemoteException {
        super();
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.customerClientUUID = Objects.requireNonNull(customerClientUUID);
        this.bike = Objects.requireNonNull(bike);
    }

    public boolean sameCustomerClientUUID(String uuid) {
        return customerClientUUID.toString().equals(Objects.requireNonNull(uuid));
    }

    @Override
    public Date getStart() throws RemoteException {
        return start;
    }

    @Override
    public Date getEnd() throws RemoteException {
        return end;
    }

    @Override
    public UUID getCustomerClientUUID() throws RemoteException {
        return customerClientUUID;
    }

    @Override
    public BikeService getBike() throws RemoteException {
        return bike;
    }

    @Override
    public String toString() {
        return "Rent{" +
                "start=" + start +
                ", end=" + end +
                ", customerClientUUID=" + customerClientUUID.toString() +
                ", bike=" + bike +
                '}';
    }
}

package rmi.bike.models.rent;


import rmi.bike.interfaces.rent.RentService;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Rent extends UnicastRemoteObject implements RentService {
    private final Date start;
    private final Date end;
    private final UUID customerClientUUID;

    public Rent(Date start, Date end, UUID customerClientUUID) throws RemoteException {
        super();
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.customerClientUUID = Objects.requireNonNull(customerClientUUID);
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
    public String toString() {
        return "Rent{" +
                "start=" + start +
                ", end=" + end +
                ", customerClientUUID=" + customerClientUUID.toString() +
                '}';
    }
}

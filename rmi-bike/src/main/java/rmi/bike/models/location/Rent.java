package rmi.bike.models.location;


import rmi.bike.interfaces.rent.RentService;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;

public class Rent extends UnicastRemoteObject implements RentService {
    private final Date start;
    private final Date end;
    private final CustomerService customerClient;

    public Rent(Date start, Date end, CustomerService customerClient) throws RemoteException {
        super();
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.customerClient = Objects.requireNonNull(customerClient);
    }

    @Override
    public Date getEnd() throws RemoteException {
        return end;
    }

    @Override
    public String toString() {
        String customerClientStr = null;

        try {
            customerClientStr = customerClient.getFirstName();
        } catch (RemoteException e) { }

        return "Rent{" +
                "start=" + start +
                ", end=" + end +
                ", customerClient=" + customerClientStr +
                '}';
    }
}

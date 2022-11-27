package web.service.rmi.bike.models.location;

import web.service.rmi.bike.interfaces.location.LocationInterface;
import web.service.rmi.customer.interfaces.customer.CustomerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;

public class Location extends UnicastRemoteObject implements LocationInterface {
    private final Date start;
    private final Date end;
    private final CustomerInterface customerClient;

    public Location(Date start, Date end, CustomerInterface customerClient) throws RemoteException {
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

        return "Location{" +
                "start=" + start +
                ", end=" + end +
                ", customerClient=" + customerClientStr +
                '}';
    }
}

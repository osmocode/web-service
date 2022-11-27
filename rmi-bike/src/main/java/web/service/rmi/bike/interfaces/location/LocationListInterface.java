package web.service.rmi.bike.interfaces.location;

import web.service.rmi.customer.interfaces.customer.CustomerInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LocationListInterface extends Remote {
    Optional<List<? extends LocationInterface>> getLocationByCustomer(String uuid) throws RemoteException;
    void addLocation(Date start, Date end, CustomerInterface customerClient) throws RemoteException;
}

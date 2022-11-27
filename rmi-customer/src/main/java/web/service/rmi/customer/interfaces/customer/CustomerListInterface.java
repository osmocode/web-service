package web.service.rmi.customer.interfaces.customer;

import web.service.rmi.customer.models.CustomerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

public interface CustomerListInterface extends Remote {
    void add (String firstName, String lastName, CustomerType customerType) throws RemoteException;
    Optional<CustomerInterface> getCustomerByUUID(String uuid) throws RemoteException;
}

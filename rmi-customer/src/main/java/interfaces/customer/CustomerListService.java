package interfaces.customer;

import models.CustomerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Optional;

public interface CustomerListService extends Remote {
    void add (String firstName, String lastName, CustomerType customerType) throws RemoteException;
    Optional<CustomerService> getCustomerByUUID(String uuid) throws RemoteException;
}

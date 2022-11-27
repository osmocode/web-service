package rmi.customer.interfaces;

import rmi.customer.models.CustomerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface CustomerListService extends Remote {
    Map<UUID, CustomerService> getAll() throws RemoteException;
    void add (String firstName, String lastName, CustomerType customerType) throws RemoteException;
    Optional<CustomerService> getCustomerByUUID(String uuid) throws RemoteException;
}

package rmi.customer.interfaces;

import rmi.customer.models.CustomerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface CustomerListService extends Remote {
    Map<UUID, ? extends CustomerService> getAll() throws RemoteException;
    Map<UUID, ? extends CustomerService> add (String firstName, String lastName, CustomerType customerType, String Password) throws RemoteException;
    CustomerService getCustomerByUUID(String uuid) throws RemoteException;
}

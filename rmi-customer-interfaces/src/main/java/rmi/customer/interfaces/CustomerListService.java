package rmi.customer.interfaces;

import rmi.customer.models.CustomerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

public interface CustomerListService extends Remote {
    Map<UUID, ? extends CustomerService> getAll() throws RemoteException;
    UUID add(String firstName, String lastName, CustomerType customerType, String username, String password) throws RemoteException;
    CustomerService getCustomerByUUID(String uuid) throws RemoteException;

    UUID login (String username, String password) throws RemoteException;
    UUID isLogged (UUID token) throws RemoteException;
    UUID logOut(UUID token) throws RemoteException;
}

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
    Map<UUID, ? extends CustomerService> add(String firstName, String lastName, CustomerType customerType, String username, String password) throws RemoteException;
    CustomerService getCustomerByUUID(String uuid) throws RemoteException;

    UUID login (String username, String password) throws RemoteException;
    UUID isLogged (UUID token) throws RemoteException;
    UUID logOut(UUID token) throws RemoteException;

    List<UUID> getBasket(String uuid) throws RemoteException;
    void addInBasket(String customerId, String bikeId) throws RemoteException;
    boolean canBuyBasket(String uuid);
    void buyBasket(String uuid);
}

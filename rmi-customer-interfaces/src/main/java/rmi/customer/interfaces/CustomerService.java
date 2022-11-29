package rmi.customer.interfaces;

import rmi.customer.models.CustomerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface CustomerService extends Remote {
    String getFirstName() throws RemoteException;
    String getLastName() throws RemoteException;
    CustomerType getCustomerType() throws RemoteException;
    String getUsername() throws RemoteException;
    String getPassword() throws RemoteException;
    List<UUID> getBikes() throws RemoteException;
    UUID getActualBikeRent() throws RemoteException;
    void setActualBikeRent(String uuid) throws RemoteException;
    boolean canRent() throws RemoteException;
    boolean canProposeBike() throws RemoteException;
}
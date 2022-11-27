package interfaces.customer;

import models.CustomerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface CustomerService extends Remote {
    UUID getUUID() throws RemoteException;
    String getFirstName() throws RemoteException;
    String getLastName() throws RemoteException;
    CustomerType getCustomerType() throws RemoteException;
    void setCustomerType(CustomerType customerType) throws RemoteException;
}
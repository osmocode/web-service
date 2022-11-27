package web.service.rmi.customer.interfaces.customer;

import web.service.rmi.customer.models.CustomerType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface CustomerInterface extends Remote {
    UUID getUUID() throws RemoteException;
    String getFirstName() throws RemoteException;
    String getLastName() throws RemoteException;
    CustomerType getCustomerType() throws RemoteException;
    void setCustomerType(CustomerType customerType) throws RemoteException;
}
package rmi.bike.interfaces.rent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.UUID;

public interface RentService extends Remote {
    Date getStart() throws RemoteException;
    Date getEnd() throws RemoteException;
    UUID getCustomerClientUUID() throws RemoteException;
}

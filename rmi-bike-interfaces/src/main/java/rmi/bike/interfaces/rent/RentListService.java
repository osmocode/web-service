package rmi.bike.interfaces.rent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface RentListService extends Remote {
    Map<UUID, ? extends RentService> getAll() throws RemoteException;
    RentService getRentByUUID(String uuid) throws RemoteException;
    Map<UUID, ? extends RentService> getRentByCustomerUUID(String customerUUID) throws RemoteException;
    Map<UUID, ? extends RentService> add(Date start, Date end, UUID customerClientUUID, UUID bikeUUID) throws RemoteException;
    Map<UUID, ? extends RentService> getRentsWithNoFeedbackByCustomer(String uuid) throws RemoteException;
    long getNumberOfOnGoingRents() throws RemoteException;
    long getNumberOfRentsPlanned() throws RemoteException;
}

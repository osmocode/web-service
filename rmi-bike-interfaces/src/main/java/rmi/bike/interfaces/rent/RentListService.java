package rmi.bike.interfaces.rent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface RentListService extends Remote {
    Map<UUID, ? extends RentService> getAll() throws RemoteException;
    Optional<RentService> getRentByUUID(String uuid) throws RemoteException;
    void add(Date start, Date end, UUID customerClientUUID) throws RemoteException;
}

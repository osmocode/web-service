package rmi.bike.interfaces.rent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RentListService extends Remote {
    //Optional<List<? extends RentService>> getRentByCustomer(String uuid) throws RemoteException;

    void add(Date start, Date end, UUID customerClientUUID) throws RemoteException;
}

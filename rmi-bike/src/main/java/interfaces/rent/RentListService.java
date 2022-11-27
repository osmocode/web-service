package interfaces.rent;

import interfaces.customer.CustomerService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RentListService extends Remote {
    Optional<List<? extends RentService>> getRentByCustomer(String uuid) throws RemoteException;

    void add(Date start, Date end, CustomerService customerClient) throws RemoteException;
}

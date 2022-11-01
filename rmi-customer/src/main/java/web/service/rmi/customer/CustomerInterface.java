package web.service.rmi.customer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerInterface extends Remote {

    String getName() throws RemoteException;

}
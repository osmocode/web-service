package web.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerInterface extends Remote {

    String getTest() throws RemoteException;

}

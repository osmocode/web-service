package web.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BikeInterface extends Remote {
    String getTest() throws RemoteException;
}

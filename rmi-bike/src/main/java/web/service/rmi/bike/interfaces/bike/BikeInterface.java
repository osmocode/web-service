package web.service.rmi.bike.interfaces.bike;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BikeInterface extends Remote {
    void setName(String name) throws RemoteException;
    String getName() throws RemoteException;
}

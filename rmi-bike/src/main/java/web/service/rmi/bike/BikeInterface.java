package web.service.rmi.bike;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BikeInterface extends Remote {

    String getName() throws RemoteException;

}

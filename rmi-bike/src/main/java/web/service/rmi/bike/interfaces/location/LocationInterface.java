package web.service.rmi.bike.interfaces.location;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface LocationInterface extends Remote {
    Date getEnd() throws RemoteException;
}

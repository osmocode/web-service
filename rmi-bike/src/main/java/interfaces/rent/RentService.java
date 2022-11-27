package interfaces.rent;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RentService extends Remote {
    Date getEnd() throws RemoteException;
}

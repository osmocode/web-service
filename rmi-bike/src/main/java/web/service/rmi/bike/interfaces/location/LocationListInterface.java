package web.service.rmi.bike.interfaces.location;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

public interface LocationListInterface extends Remote {
    Optional<List<LocationInterface>> getLocationByCustomer(long id) throws RemoteException;
}

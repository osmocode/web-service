package rmi.bike.interfaces.bike;


import rmi.bike.models.BikeState;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

public interface BikeListService extends Remote {
    Map<UUID, ? extends BikeService> getAll() throws RemoteException;
    BikeService getBikeByUUID(String uuid) throws RemoteException;
    Map<UUID, ? extends BikeService> add(String label, String description, UUID ownerUUID, BikeState bikeState) throws RemoteException;
}

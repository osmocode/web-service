package rmi.bike.interfaces.bike;


import rmi.bike.models.BikeState;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BikeListService extends Remote {
    Map<UUID, ? extends BikeService> getAll() throws RemoteException;
    BikeService getBikeByUUID(String uuid) throws RemoteException;
    Map<UUID, ? extends BikeService> add(String label, String desc, UUID ownerUUID, BikeState bikeState) throws RemoteException;
}

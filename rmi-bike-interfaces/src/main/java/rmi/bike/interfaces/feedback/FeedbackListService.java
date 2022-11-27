package rmi.bike.interfaces.feedback;


import rmi.bike.models.BikeState;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface FeedbackListService extends Remote {
    Map<UUID, ? extends FeedbackService> getAll() throws RemoteException;
    Optional<FeedbackService> getFeedbackByUUID(String uuid) throws RemoteException;
    void add(Date date, int note, String comment, BikeState bikeState, UUID uuid) throws RemoteException;
}

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
    FeedbackService getFeedbackByUUID(String uuid) throws RemoteException;
    Map<UUID, ? extends FeedbackService> add(Date date, int note, String comment, BikeState bikeState, UUID rentUUID) throws RemoteException;
}

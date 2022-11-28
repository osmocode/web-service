package rmi.bike.models.bike;

import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;
import rmi.bike.models.rent.Rent;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class Bike extends UnicastRemoteObject implements BikeService {
    private final Image image;
    private final UUID ownerId;
    private final BikeState bikeState;
    private final ArrayList<FeedbackService> feedbackHistory = new ArrayList<>();
    private final ArrayBlockingQueue<Rent> rentQueue = new ArrayBlockingQueue<>(20);

    public Bike(Image image, UUID ownerUUID, BikeState bikeState) throws RemoteException {
        super();
        this.image = image;
        this.ownerId = ownerUUID;
        this.bikeState = bikeState;
    }

    @Override
    public Image getImage() throws RemoteException {
        return image;
    }

    @Override
    public UUID getOwnerId() throws RemoteException {
        return ownerId;
    }

    @Override
    public BikeState getBikeState() throws RemoteException {
        return bikeState;
    }

    @Override
    public List<? extends FeedbackService> getFeedbackHistory() throws RemoteException {
        return feedbackHistory;
    }

    @Override
    public List<? extends RentService> getRentQueue() throws RemoteException {
        return rentQueue.stream().toList();
    }

    @Override
    public float getAverageNote() throws RemoteException {
        // TODO
        return 0;
    }
}

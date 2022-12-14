package rmi.bike.models.bike;

import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.models.BikeState;
import rmi.bike.models.feedback.Feedback;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class Bike extends UnicastRemoteObject implements BikeService {
    private final String label;
    private final String description;
    private Image image;
    private final UUID ownerId;
    private final BikeState bikeState;
    private final ArrayList<UUID> feedbackHistory = new ArrayList<>();
    private final ArrayBlockingQueue<UUID> rentQueue = new ArrayBlockingQueue<>(20);

    public Bike(String label, String description, UUID ownerUUID, BikeState bikeState) throws RemoteException {
        super();
        this.label = Objects.requireNonNull(label);
        this.description = description == null ? "" : description;
        this.ownerId = Objects.requireNonNull(ownerUUID);
        this.bikeState = Objects.requireNonNull(bikeState);
    }

    public boolean addFeedbackHistory(UUID feedback) {
        return feedbackHistory.add(Objects.requireNonNull(feedback));
    }

    public void addRentQueue(UUID rent) throws InterruptedException {
        rentQueue.put(Objects.requireNonNull(rent));
    }

    public void removeRentQueue() throws InterruptedException {
        rentQueue.remove();
    }

    @Override
    public String getLabel() throws RemoteException {
        return label;
    }

    @Override
    public String getDescription() throws RemoteException {
        return description;
    }

    @Override
    public Image getImage() throws RemoteException {
        return image;
    }

    @Override
    public void setImage(Image image) throws RemoteException {
        this.image = image;
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
    public List<UUID> getFeedbackHistory() throws RemoteException {
        return feedbackHistory;
    }

    @Override
    public List<UUID> getRentQueue() throws RemoteException {
        return rentQueue.stream().toList();
    }

    @Override
    public float getAverageNote() throws RemoteException {
        return 0F;
        /*
        return (float) feedbackHistory.stream().mapToInt(feedback -> {
                try {
                    return feedback.getNote();
                } catch (RemoteException e) {
                    throw new RuntimeException(e.getCause());
            }}).filter(integer -> integer != 0).average().orElse(Double.NaN);

         */
    }

    @Override
    public boolean canBeRent() throws RemoteException {
        return rentQueue.size() > 1;
    }

    @Override
    public boolean canBeSale() throws RemoteException {
        return feedbackHistory.size() > 0;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", ownerId=" + ownerId.toString() +
                ", bikeState=" + bikeState +
                ", feedbackHistory=" + feedbackHistory +
                ", rentQueue=" + rentQueue +
                '}';
    }
}

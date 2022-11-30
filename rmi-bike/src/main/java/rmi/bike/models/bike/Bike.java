package rmi.bike.models.bike;

import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;
import rmi.bike.models.feedback.Feedback;
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
    private final String label;
    private String description;
    private Image image;
    private final UUID ownerId;
    private final BikeState bikeState;
    private final ArrayList<Feedback> feedbackHistory = new ArrayList<>();
    private final ArrayBlockingQueue<Rent> rentQueue = new ArrayBlockingQueue<>(20);

    public Bike(String label, String desc, UUID ownerUUID, BikeState bikeState) throws RemoteException {
        super();
        this.label = Objects.requireNonNull(label);
        if (desc == null) {
            this.description = "";
        } else {
            this.description = desc;
        }
        this.ownerId = Objects.requireNonNull(ownerUUID);
        this.bikeState = Objects.requireNonNull(bikeState);
    }

    public boolean addFeedbackHistory(Feedback feedback) {
        return feedbackHistory.add(Objects.requireNonNull(feedback));
    }

    public void addRentQueue(Rent rent) throws InterruptedException {
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
        if (description == null) {
            return "";
        }
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
    public List<? extends FeedbackService> getFeedbackHistory() throws RemoteException {
        return feedbackHistory;
    }

    @Override
    public List<? extends RentService> getRentQueue() throws RemoteException {
        return rentQueue.stream().toList();
    }

    @Override
    public float getAverageNote() throws RemoteException {
        return (float) feedbackHistory.stream().filter(feedback -> feedback.getNote() != -1).mapToDouble(Feedback::getNote).average().orElse(Double.NaN);
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
                ", image=" + image +
                ", ownerId=" + ownerId.toString() +
                ", bikeState=" + bikeState +
                ", feedbackHistory=" + feedbackHistory +
                ", rentQueue=" + rentQueue +
                '}';
    }
}

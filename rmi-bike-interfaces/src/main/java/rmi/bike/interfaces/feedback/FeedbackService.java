package rmi.bike.interfaces.feedback;

import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;

import java.rmi.Remote;
import java.util.Date;
import java.util.UUID;

public interface FeedbackService extends Remote {
    Date getDate() throws RuntimeException;
    int getNote() throws RuntimeException;
    String getComment() throws RuntimeException;
    BikeState getBikeState() throws RuntimeException;
    RentService getRent() throws RuntimeException;
}

package web.service.rmi.bike.interfaces.feedback;

import web.service.rmi.bike.models.BikeState;

import java.rmi.Remote;
import java.util.Date;

public interface FeedbackInterface extends Remote {
    Date getDate() throws RuntimeException;
    int getNote() throws RuntimeException;
    String getComment() throws RuntimeException;
    BikeState getBikeState() throws RuntimeException;
}

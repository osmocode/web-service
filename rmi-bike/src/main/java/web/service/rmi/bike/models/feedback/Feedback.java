package web.service.rmi.bike.models.feedback;

import web.service.rmi.bike.interfaces.feedback.FeedbackInterface;
import web.service.rmi.bike.models.BikeState;
import web.service.rmi.bike.models.location.Location;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;

public class Feedback extends UnicastRemoteObject implements FeedbackInterface {
    private final Date date;
    private final int note;
    private final String comment;
    private final BikeState bikeState;
    private Location location;

    public Feedback(Date date, int note, String comment, BikeState bikeState, Location location) throws RemoteException {
        if (note > 5 || note < -1) {
            throw new IllegalArgumentException("-1 <= note <= 5");
        }

        this.date = Objects.requireNonNull(date);
        this.note = note;
        this.comment = comment;
        this.bikeState = bikeState == null ? BikeState.BAD : bikeState;
        this.location = Objects.requireNonNull(location);
    }

    @Override
    public Date getDate() throws RuntimeException {
        return date;
    }

    @Override
    public int getNote() throws RuntimeException {
        return note;
    }

    @Override
    public String getComment() throws RuntimeException {
        return comment;
    }

    @Override
    public BikeState getBikeState() throws RuntimeException {
        return bikeState;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "date=" + date +
                ", note=" + note +
                ", comment='" + comment + '\'' +
                ", bikeState=" + bikeState +
                ", location=" + location +
                '}';
    }
}

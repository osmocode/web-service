package models.feedback;

import interfaces.feedback.FeedbackService;
import models.BikeState;
import models.location.Rent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;

public class Feedback extends UnicastRemoteObject implements FeedbackService {
    private final Date date;
    private final int note;
    private final String comment;
    private final BikeState bikeState;
    private Rent rent;

    public Feedback(Date date, int note, String comment, BikeState bikeState, Rent rent) throws RemoteException {
        if (note > 5 || note < -1) {
            throw new IllegalArgumentException("-1 <= note <= 5");
        }

        this.date = Objects.requireNonNull(date);
        this.note = note;
        this.comment = comment;
        this.bikeState = bikeState == null ? BikeState.BAD : bikeState;
        this.rent = Objects.requireNonNull(rent);
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
                ", rent=" + rent +
                '}';
    }
}

package rmi.bike.models.feedback;

import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.BikeState;
import rmi.bike.models.rent.Rent;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Objects;

public class Feedback extends UnicastRemoteObject implements FeedbackService {
    private final Date date;
    private final int note;
    private final String comment;
    private final BikeState bikeState;
    private final Rent rent;

    public Feedback(Date date, int note, String comment, BikeState bikeState, Rent rent) throws RemoteException {
        if (note > 5 || note < 0) {
            throw new IllegalArgumentException("0 <= note <= 5");
        }

        if (note == 0 && comment != null) {
            if (comment.isBlank() || comment.isEmpty()) {
                throw new IllegalArgumentException("Comment AND Note  or  NO comment AND NO note");
            }
        } else if (note != 0 && comment == null) {
            throw new IllegalArgumentException("Comment AND Note  or  NO comment AND NO note");
        }

        this.date = Objects.requireNonNull(date);
        this.note = note;
        this.comment = comment;
        this.bikeState = bikeState;
        this.rent = Objects.requireNonNull(rent);
    }

    @Override
    public Date getDate() throws RemoteException {
        return date;
    }

    @Override
    public int getNote() throws RemoteException {
        return note;
    }

    @Override
    public String getComment() throws RemoteException {
        return comment;
    }

    @Override
    public BikeState getBikeState() throws RemoteException {
        return bikeState;
    }

    @Override
    public RentService getRent() throws RemoteException {
        return rent;
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

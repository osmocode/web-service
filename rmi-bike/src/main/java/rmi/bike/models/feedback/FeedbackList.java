package rmi.bike.models.feedback;


import rmi.bike.ApplicationContext;
import rmi.bike.interfaces.feedback.FeedbackListService;
import rmi.bike.interfaces.feedback.FeedbackService;
import rmi.bike.models.BikeState;
import rmi.bike.models.bike.Bike;
import rmi.bike.models.rent.Rent;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.*;

public class FeedbackList extends UnicastRemoteObject implements FeedbackListService {
    private final ApplicationContext context;
    private final Map<UUID, Feedback> feedbacks = new HashMap<>();

    public FeedbackList(ApplicationContext context) throws RemoteException, ParseException {
        super();
        this.context = Objects.requireNonNull(context);

        if (context.DEMO) {
            addFeedbackDemo();
        }
    }

    @Override
    public Map<UUID, ? extends FeedbackService> getAll() throws RemoteException {
        return feedbacks;
    }

    @Override
    public FeedbackService getFeedbackByUUID(String uuid) throws RemoteException {
        return feedbacks.get(Objects.requireNonNull(uuid));
    }

    @Override
    public Map<UUID, ? extends FeedbackService> add(Date date, int note, String comment, BikeState bikeState, UUID rentUUID) throws RemoteException {
        UUID uuid = rentUUID;
        Feedback feedback;

        var rent = (Rent) context.getRents().getRentByUUID(rentUUID.toString());
        if (rent == null) {
            return null;
        }

        try {
            feedback = new Feedback(date, note, comment, bikeState, rent);
        } catch (NullPointerException e) {
            return null;
        }

        // Add in feedbacks
        if (feedbacks.putIfAbsent(uuid, feedback) != null) {
            return null;
        }

        // Add in Bike.feedbackHistory
        var bike = (Bike) context.getBikes().getBikeByUUID(rent.getBike().toString());
        if (bike == null) {
            return null;
        }

        if (!bike.addFeedbackHistory(feedback)) {
            return null;
        }

        // Remove rent in Bike.rentQueue
        try {
            bike.removeRentQueue();
        } catch (InterruptedException e) {
            return null;
        }

        return Map.of(uuid, feedback);
    }

    @Override
    public String toString() {
        return "FeedbackList{" +
                "context=" + context +
                ", feedbacks=" + feedbacks +
                '}';
    }

    private void addFeedbackDemo() throws ParseException, RemoteException {
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000000"), new Feedback(context.parseDate("8/04/2022"), 3, "comment 1", BikeState.BAD, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000000").toString())));
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000001"), new Feedback(context.parseDate("18/04/2022"), -1, null, null, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000000").toString())));
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000002"), new Feedback(context.parseDate("19/05/2022"), -1, null, BikeState.VERY_BAD, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000001").toString())));
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000003"), new Feedback(context.parseDate("01/04/2022"), 2, "comment 2", null, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000002").toString())));
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000004"), new Feedback(context.parseDate("13/05/2022"), 3, "comment 3", BikeState.VERY_GOOD, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000003").toString())));
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000005"), new Feedback(context.parseDate("14/06/2022"), 1, null, null, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000003").toString())));
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000006"), new Feedback(context.parseDate("08/07/2022"), 0, "comment 4", BikeState.GOOD, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000003").toString())));
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000007"), new Feedback(context.parseDate("17/04/2022"), 2, null, BikeState.CORRECT, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000004").toString())));
        feedbacks.put(UUID.fromString("00000000-0000-0000-0000-00000008"), new Feedback(context.parseDate("20/04/2022"), 5, "comment 5", null, (Rent) context.getRents().getRentByUUID(UUID.fromString("00000000-0000-0000-0000-00000005").toString())));
    }
}

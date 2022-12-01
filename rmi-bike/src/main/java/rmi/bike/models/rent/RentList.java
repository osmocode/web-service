package rmi.bike.models.rent;

import rmi.bike.ApplicationContext;
import rmi.bike.interfaces.rent.RentListService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.bike.Bike;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class RentList extends UnicastRemoteObject implements RentListService {
    private final ApplicationContext context;
    private final Map<UUID, Rent> rents = new ConcurrentHashMap<>();

    public RentList(ApplicationContext context) throws RemoteException, ParseException {
        super();
        this.context = Objects.requireNonNull(context);

        if (context.DEMO) {
            addRentDemo();
        }
    }

    @Override
    public Map<UUID, ? extends RentService> getAll() throws RemoteException {
        return rents;
    }

    @Override
    public RentService getRentByUUID(String uuid) throws RemoteException {
        return rents.get(UUID.fromString(Objects.requireNonNull(uuid)));
    }

    @Override
    public Map<UUID, ? extends RentService> add(Date start, Date end, UUID customerClientUUID, UUID bikeUUID) throws RemoteException {
        UUID uuid;
        Rent rent;

        var bike = (Bike) context.getBikes().getBikeByUUID(bikeUUID.toString());
        if (bike == null) {
            return null;
        }

        try {
            rent = new Rent(start, end, customerClientUUID, bike);
        } catch (NullPointerException e) {
            return null;
        }

        // We check if the location is not straddling another location of the user
        if (getRentsWithNoFeedbackByCustomer(customerClientUUID.toString()).entrySet().stream().noneMatch(uuidEntry -> {
            try {
                var startR = uuidEntry.getValue().getStart();
                var endR = uuidEntry.getValue().getEnd();
                return start.before(endR); //n_start > end && n_end < start && n_start < n_end
            } catch (RemoteException e) {
                return false;
            }
        })) {
            return null;
        }

        // Add in rents
        do {
            uuid = UUID.randomUUID();
        } while (rents.putIfAbsent(uuid, rent) != null);

        // Add in Bike.rentQueue
        try {
            bike.addRentQueue(rent);
        } catch (InterruptedException e) {
            return null;
        }

        return Map.of(uuid, rent);
    }

    @Override
    public Map<UUID, ? extends RentService> getRentsWithNoFeedbackByCustomer(String uuid) throws RemoteException {
        Objects.requireNonNull(uuid);

        return rents.entrySet().stream()
                .filter(uuidRentEntry -> uuidRentEntry.getValue().sameCustomerClientUUID(uuid))
                .filter(uuidRentEntry -> {
                    try {
                        return context.getFeedbacks().getFeedbackByUUID(uuidRentEntry.getKey().toString()) == null;
                    } catch (RemoteException e) {
                        return false;
                    }
                })
                .collect(Collectors.toMap(uuidRentEntry -> uuidRentEntry.getKey(), uuidRentEntry -> uuidRentEntry.getValue()));
    }

    @Override
    public String toString() {
        return "RentList{" +
                "context=" + context +
                ", rents=" + rents +
                '}';
    }

    private void addRentDemo() throws RemoteException, ParseException {
        rents.put(UUID.fromString("00000000-0000-0000-0000-00000000"), new Rent(context.parseDate("25/04/2022"), context.parseDate("27/04/2022"), UUID.fromString("00000000-0000-0000-0000-00000001"), (Bike) context.getBikes().getBikeByUUID(UUID.fromString("00000000-0000-0000-0000-00000000").toString())));
        rents.put(UUID.fromString("00000000-0000-0000-0000-00000001"), new Rent(context.parseDate("22/04/2022"), context.parseDate("05/05/2022"), UUID.fromString("00000000-0000-0000-0000-00000001"), (Bike) context.getBikes().getBikeByUUID(UUID.fromString("00000000-0000-0000-0000-00000001").toString())));
        rents.put(UUID.fromString("00000000-0000-0000-0000-00000002"), new Rent(context.parseDate("29/04/2022"), context.parseDate("7/04/2022"), UUID.fromString("00000000-0000-0000-0000-00000001"), (Bike) context.getBikes().getBikeByUUID(UUID.fromString("00000000-0000-0000-0000-00000002").toString())));
        rents.put(UUID.fromString("00000000-0000-0000-0000-00000003"), new Rent(context.parseDate("25/05/2022"), context.parseDate("26/05/2022"), UUID.fromString("00000000-0000-0000-0000-00000002"), (Bike) context.getBikes().getBikeByUUID(UUID.fromString("00000000-0000-0000-0000-00000003").toString())));
        rents.put(UUID.fromString("00000000-0000-0000-0000-00000004"), new Rent(context.parseDate("12/04/2022"), context.parseDate("15/04/2022"), UUID.fromString("00000000-0000-0000-0000-00000003"), (Bike) context.getBikes().getBikeByUUID(UUID.fromString("00000000-0000-0000-0000-00000004").toString())));
        rents.put(UUID.fromString("00000000-0000-0000-0000-00000005"), new Rent(context.parseDate("25/04/2022"), context.parseDate("27/05/2022"), UUID.fromString("00000000-0000-0000-0000-00000004"), (Bike) context.getBikes().getBikeByUUID(UUID.fromString("00000000-0000-0000-0000-00000006").toString())));
        rents.put(UUID.fromString("00000000-0000-0000-0000-00000006"), new Rent(context.parseDate("08/04/2022"), context.parseDate("13/04/2022"), UUID.fromString("00000000-0000-0000-0000-00000004"), (Bike) context.getBikes().getBikeByUUID(UUID.fromString("00000000-0000-0000-0000-00000000").toString())));
        rents.put(UUID.fromString("00000000-0000-0000-0000-00000007"), new Rent(context.parseDate("20/04/2022"), context.parseDate("22/04/2022"), UUID.fromString("00000000-0000-0000-0000-00000004"), (Bike) context.getBikes().getBikeByUUID(UUID.fromString("00000000-0000-0000-0000-00000000").toString())));
    }
}

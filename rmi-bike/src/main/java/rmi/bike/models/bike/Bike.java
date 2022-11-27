package rmi.bike.models.bike;

import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.interfaces.rent.RentService;
import rmi.bike.models.location.Rent;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;

public class Bike extends UnicastRemoteObject implements BikeService {
    private final UUID uuid;
    private final Image image;
    private final UUID ownerId;
    private final ArrayList<Rent> rentHistory = new ArrayList<>();
    private final ArrayBlockingQueue<Rent> rentQueue = new ArrayBlockingQueue<>(20);

    private final String name;

    public Bike(UUID uuid, Image image, String ownerUUID, String name) throws RemoteException {
        super();
        this.uuid = Objects.requireNonNull(uuid);
        this.image = Objects.requireNonNull(image);
        this.ownerId = UUID.fromString(Objects.requireNonNull(ownerUUID));

        this.name = Objects.requireNonNull(name);
    }


    @Override
    public String getName() throws RemoteException {
        return name;
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
    public ArrayList<? extends RentService> getRentHistory() throws RemoteException {
        return rentHistory;
    }

    @Override
    public ArrayBlockingQueue<? extends RentService> getRentQueue() throws RemoteException {
        return rentQueue;
    }
}

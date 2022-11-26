package web.service.rmi.bike.models.bike;

import web.service.rmi.bike.interfaces.bike.BikeInterface;
import web.service.rmi.bike.models.location.Location;

import java.awt.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;

public class Bike extends UnicastRemoteObject implements BikeInterface {

    private long id;
    private Image image;
    private long ownerId;
    private List locationHistory;
    private ArrayBlockingQueue<Location> locationQueue;
    private String name;

    public Bike(String name, Image image, long ownerId, List locationHistory, ArrayBlockingQueue<Location> locationQueue) throws RemoteException {
        //Objects.requireNonNull(image);

        this.name = Objects.requireNonNull(name);

        this.id = image.hashCode();
        this.image = image;
        this.ownerId = ownerId;
        this.locationHistory = locationHistory;
        this.locationQueue = locationQueue;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}

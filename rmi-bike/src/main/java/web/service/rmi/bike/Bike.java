package web.service.rmi.bike;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Bike extends UnicastRemoteObject implements BikeInterface {

    public Bike() throws RemoteException {
        super();
    }

    @Override
    public String getName() throws RemoteException {
        return "Hello World from Bike";
    }
}
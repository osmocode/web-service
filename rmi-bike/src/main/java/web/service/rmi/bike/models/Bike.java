package web.service.rmi.bike.models;

import web.service.rmi.bike.interfaces.BikeInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Bike extends UnicastRemoteObject implements BikeInterface {
    
    public Bike() throws RemoteException {
        super();
    }

}

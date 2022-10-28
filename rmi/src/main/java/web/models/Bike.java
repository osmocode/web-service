package web.models;

import web.api.BikeInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Bike extends UnicastRemoteObject implements BikeInterface {

    public Bike() throws RemoteException {
        super();
    }

    @Override
    public String getTest() throws RemoteException {
        return "Hello World";
    }
}

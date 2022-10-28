package web.models;

import web.api.CustomerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Customer extends UnicastRemoteObject implements CustomerInterface {

    public Customer() throws RemoteException{
        super();
    }

    @Override
    public String getTest() throws RemoteException {
        return "Hello World";
    }
}

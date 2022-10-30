package web.service.rmi.customer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Customer extends UnicastRemoteObject implements CustomerInterface {

    public Customer() throws RemoteException {
        super();
    }

    @Override
    public String getName() throws RemoteException {
        return "Hello World from Customer";
    }
}

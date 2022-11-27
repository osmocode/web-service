import models.bike.BikeList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class BikeService {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        var bikes = new BikeList();
        Naming.rebind("rmi://localhost:1099/BikeListService", bikes);
        System.out.println("BikeList Service was bind successfully...");
    }
}

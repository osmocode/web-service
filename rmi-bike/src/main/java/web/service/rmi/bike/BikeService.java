package web.service.rmi.bike;

import web.service.rmi.bike.interfaces.bike.BikeListInterface;
import web.service.rmi.bike.models.bike.BikeList;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;

public class BikeService {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1010);
            BikeListInterface bikes = new BikeList();
            Naming.rebind("rmi://localhost:1010/BikeService", bikes);
            System.out.println("Bike Service was bind successfully...");
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

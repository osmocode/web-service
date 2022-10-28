package web.services;

import web.api.BikeInterface;
import web.models.Bike;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BikeService {

    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.createRegistry(1010);
            BikeInterface bike = new Bike();
            Naming.rebind("rmi://localhost:1010/BikeService", bike);
            System.out.println("Bike Service was bind successfully...");
        }catch (Exception e){
            System.err.println(e);
        }
    }
}

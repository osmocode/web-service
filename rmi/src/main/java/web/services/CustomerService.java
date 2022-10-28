package web.services;

import web.api.CustomerInterface;
import web.models.Customer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CustomerService {

    public static void main(String[] args) {
        try{
            Registry registry = LocateRegistry.createRegistry(1020);
            CustomerInterface customer = new Customer();
            Naming.rebind("rmi://localhost:1020/CustomerService", customer);
            System.out.println("Customer Service was bind successfully...");
        }catch (Exception e){
            System.err.println(e);
        }
    }

}

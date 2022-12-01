package rmi.customer;

import rmi.customer.models.CustomerList;
import rmi.customer.models.CustomerType;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ApplicationContext {
    public final boolean DEMO = true;

    private final String host;

    private final CustomerList customers;

    public ApplicationContext() throws RemoteException {
        customers = new CustomerList(this);
        host = System.getenv().getOrDefault("CUSTOMER_SERVICE_HOST", "localhost:1098/CustomerListService");
    }

    public CustomerList getCustomers() {
        return customers;
    }

    public void runServer() throws MalformedURLException, RemoteException {
        Naming.rebind("rmi://" + host, customers);
        System.out.println("CustomerList Service was bind successfully...");
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        var applicationContext = new ApplicationContext();

        LocateRegistry.createRegistry(1099);

        applicationContext.runServer();
    }
}
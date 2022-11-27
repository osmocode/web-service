import rmi.customer.models.CustomerList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CustomerService {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        var customerMap = new CustomerList();
        Naming.rebind("rmi://localhost:1099/CustomerListService", customerMap);
        System.out.println("CustomerList Service was bind successfully...");
    }
}
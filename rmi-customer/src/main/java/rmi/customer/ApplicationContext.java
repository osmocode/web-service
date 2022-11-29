package rmi.customer;

import rmi.customer.models.CustomerList;
import rmi.customer.models.CustomerType;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ApplicationContext {
    private final int PORT = 1098;
    private final boolean DEMO = true;

    private final CustomerList customers;

    public ApplicationContext() throws RemoteException {
        customers = new CustomerList(this);
    }

    public CustomerList getCustomers() {
        return customers;
    }

    public void runServer() throws MalformedURLException, RemoteException {
        Naming.rebind("rmi://localhost:" + PORT + "/CustomerListService", customers);
        System.out.println("CustomerList Service was bind successfully...");

        if (DEMO) {
            addAllDemo();
        }
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        var applicationContext = new ApplicationContext();

        LocateRegistry.createRegistry(applicationContext.PORT);

        applicationContext.runServer();
    }

    private void addAllDemo() throws RemoteException {
        addCustomerDemo();
    }

    private void addCustomerDemo() throws RemoteException {
        customers.add("Toto1", "TATA1", CustomerType.STUDENT, "TT1", "toto1");
        customers.add("Toto2", "TATA2", CustomerType.STUDENT, "TT2", "toto2");
        customers.add("Toto3", "TATA3", CustomerType.STUDENT, "TT3", "toto3");
        customers.add("Toto4", "TATA4", CustomerType.EMPLOYEE, "TT4", "toto4");
        customers.add("Toto5", "TATA5", CustomerType.EMPLOYEE, "TT5", "toto5");
        customers.add("Toto6", "TATA6", CustomerType.EMPLOYEE, "TT6", "toto6");
        customers.add("Toto7", "TATA7", CustomerType.EXTERNAL, "TT7", "toto7");
        customers.add("Toto8", "TATA8", CustomerType.EXTERNAL, "TT8", "toto8");
        customers.add("Toto9", "TATA9", CustomerType.EXTERNAL, "TT9", "toto9");
        customers.add("Toto10", "TATA10", CustomerType.EIFFEL_BIKE_CORP, "TT10", "toto10");
    }
}
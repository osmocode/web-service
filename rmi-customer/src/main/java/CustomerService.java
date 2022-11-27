import rmi.customer.models.CustomerList;
import rmi.customer.models.CustomerType;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CustomerService {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);

        var customerMap = new CustomerList();

        addAllTest(customerMap); // Optional Line

        Naming.rebind("rmi://localhost:1099/CustomerListService", customerMap);
        System.out.println("CustomerList Service was bind successfully...");
    }

    private static void addAllTest(CustomerList customerList) throws RemoteException {
        if (customerList != null) {
            addCustomerTest(customerList);
        }
    }

    private static void addCustomerTest(CustomerList customerList) throws RemoteException {
        customerList.add("Toto1", "TATA1", CustomerType.STUDENT);
        customerList.add("Toto2", "TATA2", CustomerType.STUDENT);
        customerList.add("Toto3", "TATA3", CustomerType.STUDENT);
        customerList.add("Toto4", "TATA4", CustomerType.EMPLOYEE);
        customerList.add("Toto5", "TATA5", CustomerType.EMPLOYEE);
        customerList.add("Toto6", "TATA6", CustomerType.EMPLOYEE);
        customerList.add("Toto7", "TATA7", CustomerType.EXTERNAL);
        customerList.add("Toto8", "TATA8", CustomerType.EXTERNAL);
        customerList.add("Toto9", "TATA9", CustomerType.EXTERNAL);
        customerList.add("Toto10", "TATA10", CustomerType.EIFFEL_BIKE_CORP);
    }
}
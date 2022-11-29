package rmi.customer.models;

import rmi.customer.ApplicationContext;
import rmi.customer.interfaces.CustomerListService;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerList extends UnicastRemoteObject implements CustomerListService {
    private final ApplicationContext context;
    private final Map<UUID, Customer> customers = new ConcurrentHashMap<>();

    public CustomerList(ApplicationContext context) throws RemoteException {
        super();
        this.context = Objects.requireNonNull(context);
        this.customers.put(UUID.fromString("00000000-0000-0000-0000-00000000"), new Customer("yann", "picker", CustomerType.EIFFEL_BIKE_CORP, "password"));
    }

    @Override
    public Map<UUID, ? extends CustomerService> getAll() throws RemoteException {
        return customers;
    }

    @Override
    public CustomerService add(String firstName, String lastName, CustomerType customerType, String password) throws RemoteException {
        Customer customer;

        try {
            customer = new Customer(firstName, lastName, customerType, password);
        } catch (NullPointerException e) {
            return null;
        }

        while (customers.putIfAbsent(UUID.randomUUID(), customer) != null) {}

        return customer;
    }

    @Override
    public CustomerService getCustomerByUUID(String uuid) throws RemoteException {
        return customers.get(UUID.fromString(Objects.requireNonNull(uuid)));
    }

    @Override
    public String toString() {
        return "CustomerList{" +
                "context=" + context +
                ", customers=" + customers +
                '}';
    }
}

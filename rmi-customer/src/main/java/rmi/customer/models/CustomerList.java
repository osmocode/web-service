package rmi.customer.models;

import rmi.customer.interfaces.CustomerListService;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerList extends UnicastRemoteObject implements CustomerListService {

    private final Map<UUID, Customer> customerMap = new ConcurrentHashMap<>();

    public CustomerList() throws RemoteException {
        super();
    }

    @Override
    public Map<UUID, ? extends CustomerService> getAll() throws RemoteException {
        return customerMap;
    }

    @Override
    public void add(String firstName, String lastName, CustomerType customerType) throws RemoteException {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(customerType);

        while (customerMap.putIfAbsent(UUID.randomUUID(), new Customer(firstName, lastName, customerType)) != null) {}
    }

    @Override
    public Optional<CustomerService> getCustomerByUUID(String uuid) throws RemoteException {
        return Optional.ofNullable(customerMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }
}

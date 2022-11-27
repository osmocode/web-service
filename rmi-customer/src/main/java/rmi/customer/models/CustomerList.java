package rmi.customer.models;

import rmi.customer.interfaces.CustomerListService;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerList extends UnicastRemoteObject implements CustomerListService {

    private final Map<UUID, Customer> customerMap = new ConcurrentHashMap<>();

    public CustomerList() throws RemoteException {
        super();
    }

    @Override
    public void add(String firstName, String lastName, CustomerType customerType) throws RemoteException {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(customerType);

        boolean create;

        do {
            var uuid = UUID.randomUUID();
            create = customerMap.putIfAbsent(uuid, new Customer(uuid, firstName, lastName, customerType)) == null;
        } while (!create);
    }

    @Override
    public Optional<CustomerService> getCustomerByUUID(String uuid) throws RemoteException {
        return Optional.ofNullable(customerMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }
}

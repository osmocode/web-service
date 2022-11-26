package web.service.rmi.customer.models.customer;

import web.service.rmi.customer.interfaces.customer.CustomerInterface;
import web.service.rmi.customer.interfaces.customer.CustomerListInterface;
import web.service.rmi.customer.models.CustomerType;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerList extends UnicastRemoteObject implements CustomerListInterface {
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
    public Optional<CustomerInterface> getCustomerByUUID(String uuid) throws RemoteException {
        return Optional.ofNullable(customerMap.get(UUID.fromString(Objects.requireNonNull(uuid))));
    }
}

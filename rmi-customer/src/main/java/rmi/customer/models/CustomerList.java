package rmi.customer.models;

import rmi.customer.ApplicationContext;
import rmi.customer.interfaces.CustomerListService;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CustomerList extends UnicastRemoteObject implements CustomerListService {
    private final ApplicationContext context;

    private final Map<UUID, Customer> customers = new ConcurrentHashMap<>();
    private final Map<UUID, UUID> connexionToken = new ConcurrentHashMap<>();

    public CustomerList(ApplicationContext context) throws RemoteException {
        super();
        this.context = Objects.requireNonNull(context);
        this.customers.put(UUID.fromString("00000000-0000-0000-0000-00000000"), new Customer("yann", "picker", CustomerType.EIFFEL_BIKE_CORP,"ypicker", "password"));
    }

    @Override
    public Map<UUID, ? extends CustomerService> getAll() throws RemoteException {
        return customers;
    }

    @Override
    public UUID add(String firstName, String lastName, CustomerType customerType, String username, String password) throws RemoteException {
        Customer customer;
        UUID uuid;

        try {
            customer = new Customer(firstName, lastName, customerType, username, password);
        } catch (NullPointerException e) {
            return null;
        }

        do {
            uuid = UUID.randomUUID();
        } while (customers.putIfAbsent(uuid, customer) != null);

        return uuid;
    }

    @Override
    public CustomerService getCustomerByUUID(String uuid) throws RemoteException {
        return customers.get(UUID.fromString(Objects.requireNonNull(uuid)));
    }

    @Override
    public UUID login(String username, String password) throws RemoteException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        var customer = customers.entrySet()
                .stream()
                .filter(uuidCustomerEntry -> uuidCustomerEntry.getValue().verifLogin(username, password))
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toList(), list -> {
                                    if (list.size() != 1) {
                                        throw new IllegalStateException();
                                    }
                                    return list.get(0);
                                }
                        ));

        UUID token;
        do {
            token = UUID.randomUUID();
        } while (connexionToken.putIfAbsent(token, customer.getKey()) != null);

        return token;
    }

    @Override
    public UUID isLogged(UUID token) throws RemoteException {
        return connexionToken.get(Objects.requireNonNull(token));
    }

    @Override
    public UUID logOut(UUID token) throws RemoteException {
        return connexionToken.remove(Objects.requireNonNull(token));
    }

    @Override
    public String toString() {
        return "CustomerList{" +
                "context=" + context +
                ", customers=" + customers +
                ", connexionToken=" + connexionToken +
                '}';
    }
}

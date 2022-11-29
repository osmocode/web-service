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
    private final Map<UUID, Customer> connexionToken = new ConcurrentHashMap<>();

    public CustomerList(ApplicationContext context) throws RemoteException {
        super();
        this.context = Objects.requireNonNull(context);

        if (context.DEMO) {
            addCustomerDemo();
        }
    }

    @Override
    public Map<UUID, ? extends CustomerService> getAll() throws RemoteException {
        return customers;
    }

    @Override
    public Map<UUID, ? extends CustomerService> add(String firstName, String lastName, CustomerType customerType, String username, String password) throws RemoteException {
        UUID uuid;
        Customer customer;

        try {
            customer = new Customer(firstName, lastName, customerType, username, password);
        } catch (NullPointerException e) {
            return null;
        }

        do {
            uuid = UUID.randomUUID();
        } while (customers.putIfAbsent(uuid, customer) != null);

        return Map.of(uuid, customer);
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
                            ))
                .getValue();

        UUID token;
        do {
            token = UUID.randomUUID();
        } while (connexionToken.putIfAbsent(token, customer) != null);

        return token;
    }

    @Override
    public CustomerService isLogged(UUID token) throws RemoteException {
        return connexionToken.get(Objects.requireNonNull(token));
    }

    @Override
    public CustomerService logOut(UUID token) throws RemoteException {
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

    private void addCustomerDemo() throws RemoteException {
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000000"), new Customer("Toto0", "TATA0", CustomerType.EIFFEL_BIKE_CORP, "TT0", "toto0"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000001"), new Customer("Toto1", "TATA1", CustomerType.STUDENT, "TT1", "toto1"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000002"), new Customer("Toto2", "TATA2", CustomerType.STUDENT, "TT2", "toto2"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000003"), new Customer("Toto3", "TATA3", CustomerType.STUDENT, "TT3", "toto3"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000004"), new Customer("Toto4", "TATA4", CustomerType.EMPLOYEE, "TT4", "toto4"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000005"), new Customer("Toto5", "TATA5", CustomerType.EMPLOYEE, "TT5", "toto5"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000006"), new Customer("Toto6", "TATA6", CustomerType.EMPLOYEE, "TT6", "toto6"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000007"), new Customer("Toto7", "TATA7", CustomerType.EXTERNAL, "TT7", "toto7"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000008"), new Customer("Toto8", "TATA8", CustomerType.EXTERNAL, "TT8", "toto8"));
        customers.put(UUID.fromString("00000000-0000-0000-0000-00000009"), new Customer("Toto9", "TATA9", CustomerType.EXTERNAL, "TT9", "toto9"));
    }
}

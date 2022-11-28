package rmi.customer.models;


import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Customer extends UnicastRemoteObject implements CustomerService {
    private final String firstName;
    private final String lastName;
    private CustomerType customerType;
    private List<UUID> bikes = new ArrayList<>();

    public Customer(String firstName, String lastName, CustomerType customerType) throws RemoteException {
        super();

        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.customerType = Objects.requireNonNull(customerType);
    }

    @Override
    public String getFirstName() throws RemoteException {
        return firstName;
    }

    @Override
    public String getLastName() throws RemoteException {
        return lastName;
    }

    @Override
    public CustomerType getCustomerType() throws RemoteException {
        return customerType;
    }

    @Override
    public List<UUID> getBikes() throws RemoteException {
        return bikes;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerType=" + customerType.toString() +
                ", bikes=" + bikes +
                '}';
    }
}

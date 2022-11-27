package rmi.customer.models;


import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Objects;
import java.util.UUID;

public class Customer extends UnicastRemoteObject implements CustomerService {
    private final UUID uuid;
    private final String firstName;
    private final String lastName;
    private CustomerType customerType;

    public Customer(UUID uuid, String firstName, String lastName, CustomerType customerType) throws RemoteException {
        super();

        this.uuid = Objects.requireNonNull(uuid);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.customerType = Objects.requireNonNull(customerType);
    }

    @Override
    public UUID getUUID() throws RemoteException {
        return uuid;
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
    public void setCustomerType(CustomerType customerType) throws RemoteException {
        this.customerType = Objects.requireNonNull(customerType);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "uuid=" + uuid.toString() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", customerType=" + customerType.toString() +
                '}';
    }
}

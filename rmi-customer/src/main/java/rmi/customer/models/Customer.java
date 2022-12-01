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
    private final String username;
    private final String password;
    private UUID actualBikeRent;
    private CustomerType customerType;
    private List<UUID> bikes = new ArrayList<>();

    public Customer(String firstName, String lastName, CustomerType customerType, String username, String password) throws RemoteException {
        super();

        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.customerType = Objects.requireNonNull(customerType);
        this.username = Objects.requireNonNull(username);
        this.password = Objects.requireNonNull(password);
    }

    public boolean verifLogin(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
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
    public String getUsername() throws RemoteException {
        return username;
    }

    @Override
    public String getPassword() throws RemoteException {
        return password;
    }

    @Override
    public List<UUID> getBikes() throws RemoteException {
        return bikes;
    }

    @Override
    public void addBike(UUID bikeId) throws RemoteException {
        this.bikes.add(bikeId);
    }

    @Override
    public UUID getActualBikeRent() throws RemoteException {
        return actualBikeRent;
    }

    @Override
    public void setActualBikeRent(String uuid) throws RemoteException {
        actualBikeRent = UUID.fromString(Objects.requireNonNull(uuid));
    }

    @Override
    public boolean canRent() throws RemoteException {
        return customerType.canRent();
    }

    @Override
    public boolean canProposeBike() throws RemoteException {
        return customerType.canProposeBike();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username=" + username + '\'' +
                ", password='" + password + '\'' +
                ", actualBikeRent=" + actualBikeRent.toString() +
                ", customerType=" + customerType +
                ", bikes=" + bikes +
                '}';
    }
}

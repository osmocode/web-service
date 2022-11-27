package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;

public class CustomerProvider {

    @JsonProperty("uuid")
    public final String uuid;

    @JsonProperty("firstName")
    public final String firstName;

    @JsonProperty("lastName")
    public final String lastName;

    public CustomerProvider(String uuid, CustomerService customerInterface) throws RemoteException {
        this.uuid = uuid;
        this.firstName = customerInterface.getFirstName();
        this.lastName = customerInterface.getLastName();
    }
}

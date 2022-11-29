package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.util.UUID;

public class CustomerProvider {

    @JsonProperty("uuid")
    public final String uuid;

    @JsonProperty("first_name")
    public final String firstName;

    @JsonProperty("last_name")
    public final String lastName;

    public CustomerProvider(UUID uuid, CustomerService customerInterface) throws RemoteException {
        this.uuid = uuid.toString();
        this.firstName = customerInterface.getFirstName();
        this.lastName = customerInterface.getLastName();
    }
}

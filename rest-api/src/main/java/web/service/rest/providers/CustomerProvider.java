package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import web.service.rmi.customer.interfaces.customer.CustomerInterface;

import java.rmi.RemoteException;

public class CustomerProvider {

    @JsonProperty("uuid")
    public final String uuid;

    @JsonProperty("firstName")
    public final String firstName;

    @JsonProperty("lastName")
    public final String lastName;

    public CustomerProvider(String uuid, CustomerInterface customerInterface) throws RemoteException {
        this.uuid = uuid;
        this.firstName = customerInterface.getFirstName();
        this.lastName = customerInterface.getLastName();
    }
}

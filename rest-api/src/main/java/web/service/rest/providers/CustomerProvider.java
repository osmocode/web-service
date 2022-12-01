package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.customer.interfaces.CustomerService;

import java.rmi.RemoteException;
import java.util.UUID;

public class CustomerProvider {

    @JsonProperty(
        value = "id",
        access = JsonProperty.Access.READ_ONLY
    )
    public String uuid;

    @JsonProperty(
        value = "last_name",
        access = JsonProperty.Access.READ_WRITE
    )
    public String lastName;

    @JsonProperty(
        value = "first_name",
        access = JsonProperty.Access.READ_WRITE
    )
    public String firstName;
    
    @JsonProperty(
        value = "password",
        access = JsonProperty.Access.READ_WRITE
    )
    public String password;

    @JsonProperty(
        value = "customer_type",
        access = JsonProperty.Access.READ_WRITE
    )
    public String customerType;

    public CustomerProvider(){}

    public CustomerProvider(UUID uuid, CustomerService customerInterface) throws RemoteException {
        this.uuid = uuid.toString();
        this.firstName = customerInterface.getFirstName();
        this.lastName = customerInterface.getLastName();
        this.customerType = customerInterface.getCustomerType().toString();
        this.password = customerInterface.getPassword();
    }
}

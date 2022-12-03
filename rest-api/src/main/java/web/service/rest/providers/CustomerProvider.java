package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.customer.interfaces.CustomerService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public class CustomerProvider {

    @JsonProperty(
        value = "id",
        access = JsonProperty.Access.READ_ONLY
    )
    public String uuid;

    @NotEmpty
    @NotNull
    @JsonProperty(
        value = "first_name",
        required = true,
        access = JsonProperty.Access.READ_WRITE
    )
    public String firstName;

    @NotEmpty
    @NotNull
    @JsonProperty(
        value = "last_name",
        required = true,
        access = JsonProperty.Access.READ_WRITE
    )
    public String lastName;

    @NotEmpty
    @NotNull
    @JsonProperty(
        value = "username",
        required = true,
        access = JsonProperty.Access.READ_WRITE
    )
    public String username;
    
    @JsonProperty(
        value = "password",
        access = JsonProperty.Access.WRITE_ONLY
    )
    public String password;

    @NotEmpty
    @NotNull
    @JsonProperty(
        value = "type",
        required = true,
        access = JsonProperty.Access.READ_WRITE
    )
    public String type;

    @JsonProperty(
        value = "bikes",
        access = JsonProperty.Access.READ_ONLY
    )
    public List<String> bikes;

    public CustomerProvider() {}

    public CustomerProvider(UUID uuid, CustomerService customerInterface) throws RemoteException {
        this.uuid = uuid.toString();
        this.firstName = customerInterface.getFirstName();
        this.lastName = customerInterface.getLastName();
        this.username = customerInterface.getUsername();
        this.type = customerInterface.getCustomerType().toString();
        this.bikes = customerInterface.getBikes().stream().map(UUID::toString).toList();

    }
}

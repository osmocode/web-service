package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.bike.interfaces.rent.RentService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.UUID;

public class RentalProvider {

    @JsonProperty(
        value = "id",
        access = JsonProperty.Access.READ_ONLY
    )
    public String uuid;

    @NotNull
    @NotEmpty
    @JsonProperty(
        value = "start",
        access = JsonProperty.Access.READ_WRITE
    )
    public String start;

    @NotNull
    @NotEmpty
    @JsonProperty(
        value = "end",
        access = JsonProperty.Access.READ_WRITE
    )
    public String end;

    @JsonProperty(
            value = "customer",
            access = JsonProperty.Access.READ_ONLY
    )
    public String customer;

    @NotNull
    @NotEmpty
    @JsonProperty(
        value = "bike",
        access = JsonProperty.Access.WRITE_ONLY
    )
    public String bike;

    public RentalProvider() {}

    public RentalProvider(UUID uuid, RentService rentService) throws RemoteException {
        this.uuid = uuid.toString();
        this.start = rentService.getStart().toString();
        this.end = rentService.getEnd().toString();
        this.customer = rentService.getCustomerClientUUID().toString();
    }
}

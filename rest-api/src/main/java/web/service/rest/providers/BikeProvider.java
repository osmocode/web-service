package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.interfaces.rent.RentService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public class BikeProvider {

    @JsonProperty(
        value = "id",
        access = JsonProperty.Access.READ_ONLY
    )
    public String uuid;

    @NotEmpty
    @NotNull
    @JsonProperty(
        value = "label",
        required = true,
        access = JsonProperty.Access.READ_WRITE
    )
    public String label;

    @JsonProperty(
        value = "desc",
        access = JsonProperty.Access.READ_WRITE
    )
    public String desc;

    @JsonProperty(
        value = "owner",
        access = JsonProperty.Access.READ_ONLY
    )
    public String owner;

    @NotEmpty
    @NotNull
    @JsonProperty(
        value = "state",
        access = JsonProperty.Access.READ_WRITE
    )
    public String state;

    @JsonProperty(
        value = "rent_history",
        access = JsonProperty.Access.READ_ONLY
    )
    public List<RentService> locationHistory;

    @JsonProperty(
        value = "rent_queue",
        access = JsonProperty.Access.READ_ONLY
    )
    public List<RentService> locationQueue;

    public BikeProvider() {}

    public BikeProvider(UUID uuid, BikeService bikeService) throws RemoteException {
        this.uuid = uuid.toString();
        this.desc = bikeService.getDescription();
        this.label = bikeService.getLabel();
        this.state = bikeService.getBikeState().toString();
        this.owner = bikeService.getOwnerId().toString();
        //this.locationHistory = bikeInterface.getLocationHistory();
        //this.locationQueue = bikeInterface.getLocationQueue();
    }

}

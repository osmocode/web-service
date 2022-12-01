package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.interfaces.rent.RentService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RentalListProvider {

    @JsonProperty(
            value = "count",
            access = JsonProperty.Access.READ_ONLY
    )
    public final int count;

    @JsonProperty(
            value = "results",
            access = JsonProperty.Access.READ_ONLY
    )
    public final List<RentalProvider> rentals;

    public RentalListProvider(Map< UUID, ? extends RentService> rents) {
            this.count = rents.size();
            this.rentals = rents.entrySet().stream().map((entry) -> {
                try {
                    return new RentalProvider(entry.getKey(), entry.getValue());
                } catch (RemoteException e) {
                    throw new RuntimeException(e.getCause());
                }
            }).toList();
        }


}

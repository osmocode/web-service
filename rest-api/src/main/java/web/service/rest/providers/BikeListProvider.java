package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.bike.interfaces.bike.BikeService;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BikeListProvider {

    @JsonProperty(
        value = "count",
        access = JsonProperty.Access.READ_ONLY
    )
    public int count;

    @JsonProperty(
        value = "results",
        access = JsonProperty.Access.READ_ONLY
    )
    public List<BikeProvider> bikes;

    public BikeListProvider(Map<UUID, ? extends BikeService> bikes) {
        this.count = bikes.size();
        this.bikes = bikes.entrySet().stream().map((entry) -> {
            try {
                return new BikeProvider(entry.getKey(), entry.getValue());
            } catch (RemoteException e) {
                throw new RuntimeException(e.getCause());
            }
        }).toList();
    }

}

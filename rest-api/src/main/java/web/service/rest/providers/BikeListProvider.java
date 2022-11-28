package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.bike.interfaces.bike.BikeListService;

import java.rmi.RemoteException;
import java.util.List;


public class BikeListProvider {

    @JsonProperty("count")
    public final Integer count;

    public BikeListProvider(BikeListService bikeListService) throws RemoteException {
        this.count = bikeListService.getAll().size();
    }

}

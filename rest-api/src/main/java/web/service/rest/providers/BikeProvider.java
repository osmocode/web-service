package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import web.service.rmi.bike.interfaces.bike.BikeInterface;

import java.rmi.RemoteException;

public class BikeProvider {

    @JsonProperty("name")
    private final String name;

    public BikeProvider(BikeInterface bikeInterface) throws RemoteException {
        this.name = bikeInterface.getName();
    }

}

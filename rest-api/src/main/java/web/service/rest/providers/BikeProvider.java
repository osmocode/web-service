package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.bike.interfaces.bike.BikeService;
import rmi.bike.interfaces.rent.RentListService;
import rmi.bike.interfaces.rent.RentService;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class BikeProvider {

    @JsonProperty("uuid")
    public final String uuid;

    @JsonProperty("name")
    public final String name;

    @JsonProperty("owner")
    public final UUID owner;

    @JsonProperty("image")
    public final Image image;

    /*
    @JsonProperty("locationHistory")
    public final List<BikeService> locationHistory;

    @JsonProperty("locationQueue")
    public final BlockingQueue<BikeService> locationQueue;
     */

    public BikeProvider(String uuid, BikeService bikeInterface) throws RemoteException {
        this.uuid = uuid;
        this.name = bikeInterface.getName();
        this.owner = bikeInterface.getOwnerId();
        this.image = bikeInterface.getImage();
        //this.locationHistory = bikeInterface.getLocationHistory();
        //this.locationQueue = bikeInterface.getLocationQueue();
    }

}

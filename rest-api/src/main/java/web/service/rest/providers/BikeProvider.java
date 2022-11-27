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

    @JsonProperty("id")
    public final String uuid;

    @JsonProperty("name")
    public final String name;

    @JsonProperty("owner")
    public final UUID owner;

    @JsonProperty("image")
    public final Image image;

    @JsonProperty("rent_history")
    public List<RentService> locationHistory;

    @JsonProperty("rent_queue")
    public List<RentService> locationQueue;


    public BikeProvider(String uuid, BikeService bikeService) throws RemoteException {
        this.uuid = uuid;
        this.name = bikeService.getName();
        this.owner = bikeService.getOwnerId();
        this.image = bikeService.getImage();
        //this.locationHistory = bikeInterface.getLocationHistory();
        //this.locationQueue = bikeInterface.getLocationQueue();
    }

}

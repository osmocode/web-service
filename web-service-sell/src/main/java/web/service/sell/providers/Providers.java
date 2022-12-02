package web.service.sell.providers;

import org.springframework.schema.web_services.Bike;
import rmi.bike.interfaces.bike.BikeService;

import java.rmi.RemoteException;

public class Providers {


    public static Bike getBike(String uuid, BikeService bikeService) throws RemoteException {
        var bike = new Bike();
        bike.setId(uuid);
        bike.setLabel(bikeService.getLabel());
        bike.setDesc(bikeService.getDescription());
        bike.setOwner(bikeService.getOwnerId().toString());
        return bike;
    }
}

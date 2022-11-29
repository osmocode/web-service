package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rmi.bike.interfaces.bike.BikeListService;
import rmi.bike.models.BikeState;
import web.service.rest.providers.BikeListProvider;
import web.service.rest.providers.BikeProvider;

import javax.validation.Valid;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.UUID;


@RestController
public class BikeController {

    @Autowired
    BikeListService service;

    @GetMapping("/bike")
    public BikeListProvider getBike() throws RemoteException {
        return new BikeListProvider(service.getAll());
    }

    @GetMapping("/bike/{id}")
    public BikeProvider getBikeById(@PathVariable("id") String uuid) throws RemoteException {
        var bike = service.getBikeByUUID(uuid);
        if (bike == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "bike not found");
        }
        return new BikeProvider(UUID.fromString(uuid), bike);
    }

    @PostMapping("/bike")
    public BikeProvider putBike(@Valid @RequestBody BikeProvider bike) throws RemoteException {
        var entry = service.add(bike.label, UUID.fromString("00000000-0000-0000-0000-00000000"), BikeState.EXCELLENT);
        if(entry == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bike not created");
        }
        var response =  entry.entrySet().stream().findFirst();
        if(response.isEmpty()){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "bike creation error");
        }
        return new BikeProvider(response.get().getKey(), response.get().getValue());
    }

}

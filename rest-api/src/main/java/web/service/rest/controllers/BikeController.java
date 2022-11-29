package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/bike")
    public BikeProvider putBike(@Valid @RequestBody BikeProvider bike) throws RemoteException {
        var entry = service.add(bike.label, UUID.fromString("00000000-0000-0000-0000-00000000"), BikeState.EXCELLENT);

        var response =  entry.entrySet().stream().findFirst().get();

        return new BikeProvider(response.getKey(), response.getValue());
    }

}

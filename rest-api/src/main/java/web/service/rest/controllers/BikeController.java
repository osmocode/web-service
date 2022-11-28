package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rmi.bike.interfaces.bike.BikeListService;
import rmi.bike.models.BikeState;
import web.service.rest.providers.BikeListProvider;
import web.service.rest.providers.BikeProvider;

import javax.validation.Valid;
import java.rmi.RemoteException;


@RestController
public class BikeController {

    @Autowired
    BikeListService service;

    @GetMapping("/bike")
    public BikeListProvider getBike() throws RemoteException {
        return new BikeListProvider(service.getAll());
    }

    @PostMapping("/bike")
    public BikeProvider put(@Valid @RequestBody BikeProvider bike) throws RemoteException {
        service.add(null, null, BikeState.EXCELLENT);
        return bike;
    }

}

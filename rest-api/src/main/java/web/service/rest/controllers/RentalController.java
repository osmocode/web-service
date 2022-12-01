package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rmi.bike.interfaces.rent.RentListService;
import rmi.customer.interfaces.CustomerListService;
import web.service.rest.providers.RentalListProvider;
import web.service.rest.providers.RentalProvider;
import web.service.rest.session.Authenticated;

import javax.validation.Valid;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;


@RestController
public class RentalController {

    @Autowired
    RentListService rentService;

    @Autowired
    CustomerListService authService;

    @GetMapping("/api/v1/rent")
    public RentalListProvider getRent() throws RemoteException {
        return new RentalListProvider(this.rentService.getAll());
    }

    @GetMapping("/api/v1/rent/{id}")
    public RentalProvider getRent(@PathVariable("id") String uuid) throws RemoteException {
        var rent = rentService.getRentByUUID(uuid);
        if (rent == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rent not found");
        }
        return new RentalProvider(UUID.fromString(uuid), rent);
    }

    @Authenticated
    @PostMapping("/api/v1/rent")
    public RentalProvider postRent(
            @RequestHeader(value = "X-Auth-Token") String token,
            @Valid @RequestBody RentalProvider rent) throws RemoteException {
        var user = authService.isLogged(UUID.fromString(token));
        var rental = rentService.add(
                Date.from(Instant.parse(rent.start)),
                Date.from(Instant.parse(rent.end)),
                user,
                UUID.fromString(rent.bike)
        );
        if (rental == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rent was not created");
        }
        var response = rental.entrySet().stream().findFirst();
        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Rent creation error");
        }
        return new RentalProvider(response.get().getKey(), response.get().getValue());
    }


}

package web.service.rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.rest.models.Bike;

@RestController
public class BikeController {

    @GetMapping("/bike")
    public Bike getBike() {
        return new Bike();
    }

}

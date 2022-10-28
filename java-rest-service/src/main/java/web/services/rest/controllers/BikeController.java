package web.services.rest.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.services.rest.models.Bike;

@RestController
public class BikeController {

    @GetMapping("/bike")
    public Bike getBike() {
        var bike = new Bike();
        bike.setName("Hello world");
        return bike;
    }

}

package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rmi.customer.interfaces.CustomerListService;
import web.service.rest.providers.CustomerListProvider;
import web.service.rest.providers.CustomerProvider;

import javax.validation.Valid;
import java.rmi.RemoteException;
import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    private CustomerListService service;


    @GetMapping("/api/v1/customer")
    public CustomerListProvider getCustomerAll() throws RemoteException {
        return new CustomerListProvider(service.getAll());
    }

    @GetMapping("/api/v1/customer/{id}")
    public CustomerProvider getCustomerById(@PathVariable("id") String uuid) throws RemoteException {
        var customer = service.getCustomerByUUID(uuid);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found...");
        }
        return new CustomerProvider(UUID.fromString(uuid), customer);
    }

    @PostMapping("/api/v1/customer")
    public CustomerProvider postCustomer(@Valid @RequestBody CustomerProvider customer) throws RemoteException {
        return customer;
    }

}

package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rmi.customer.interfaces.CustomerListService;
import rmi.customer.models.CustomerType;
import web.service.rest.providers.CustomerListProvider;
import web.service.rest.providers.CustomerProvider;

import java.rmi.RemoteException;
import java.util.UUID;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerListService service;


    @GetMapping("/customer")
    public CustomerListProvider getCustomerAll() throws RemoteException {
        return new CustomerListProvider(service.getAll());
    }

    @GetMapping("/customer/{id}")
    public CustomerProvider getCustomerById(String id) throws RemoteException {
        return new CustomerProvider(UUID.fromString(id), service.getCustomerByUUID(id));
    }

    @PostMapping("/customer")
    public CustomerProvider putCustomer(@Valid @RequestBody CustomerProvider customer) throws RemoteException {
        var entry = service.add(customer.firstName, customer.lastName,CustomerType.valueOf(customer.customerType.toUpperCase().replace(" ", "_")), customer.password);
        var response =  entry.entrySet().stream().findFirst().get();

        return new CustomerProvider(response.getKey(), response.getValue());
    }

}

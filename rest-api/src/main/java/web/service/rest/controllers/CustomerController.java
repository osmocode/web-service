package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rmi.customer.interfaces.CustomerListService;
import web.service.rest.providers.CustomerListProvider;

import java.rmi.RemoteException;

@RestController
public class CustomerController {

    @Autowired
    private CustomerListService service;


    @GetMapping("/customer")
    public CustomerListProvider getCustomerAll() throws RemoteException {
        return new CustomerListProvider(service.getAll());
    }

}

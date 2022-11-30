package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import rmi.customer.interfaces.CustomerListService;
import web.service.rest.models.LoginForm;
import web.service.rest.models.TokenForm;
import web.service.rest.providers.CustomerProvider;
import web.service.rest.session.Authenticated;

import javax.validation.Valid;
import java.rmi.RemoteException;
import java.util.UUID;

@RestController
public class AuthController {

    @Autowired
    private CustomerListService service;

    @PostMapping("/api/v1/auth/login")
    TokenForm auth(@Valid @RequestBody LoginForm form) throws RemoteException {
        var token = this.service.login(form.username, form.password);
        System.out.println(token);
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new TokenForm(token);
    }

    @Authenticated
    @GetMapping("/api/v1/auth/me")
    public CustomerProvider me(@RequestHeader(value = "X-Auth-Token") String token) throws RemoteException {
        var id = this.service.isLogged(UUID.fromString(token));
        var customer = this.service.getCustomerByUUID(id.toString());
        if (customer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        return new CustomerProvider(id, customer);
    }

    @Authenticated
    @PostMapping("/api/v1/auth/logout")
    public void logout(@RequestHeader(value = "X-Auth-Token") String token) throws RemoteException {
        this.service.logOut(UUID.fromString(token));
    }

}

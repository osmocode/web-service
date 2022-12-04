package web.service.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonProperty;

import rmi.bike.interfaces.feedback.FeedbackListService;
import rmi.bike.interfaces.rent.RentListService;
import rmi.customer.interfaces.CustomerListService;
import rmi.customer.interfaces.CustomerService;
import rmi.customer.models.CustomerType;
import web.service.rest.providers.CustomerListProvider;
import web.service.rest.providers.CustomerProvider;
import web.service.rest.providers.FeedbackListProvider;
import web.service.rest.providers.RentalListProvider;

import javax.validation.Valid;
import java.rmi.RemoteException;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    @Autowired
    private CustomerListService service;

    @Autowired
    private FeedbackListService feedbackService;

    @Autowired
    private RentListService rentService;

    @GetMapping("/api/v1/customer")
    public CustomerListProvider getCustomerAll() throws RemoteException {
        return new CustomerListProvider(service.getAll());
    }

    @GetMapping("/api/v1/customer/{id}")
    public CustomerProvider getCustomerById(@PathVariable("id") String uuid) throws RemoteException {
        var customer = service.getCustomerByUUID(uuid);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return new CustomerProvider(UUID.fromString(uuid), customer);
    }

    @GetMapping("/api/v1/customer/{id}/feedback")
    public FeedbackListProvider getCustomerFeedbackById(@PathVariable("id") String uuid) throws RemoteException {
        var customer = service.getCustomerByUUID(uuid);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        var res = feedbackService.getAll().entrySet().stream()
        .filter(t -> {
            try {
                return t.getValue().getRent().getCustomerClientUUID().equals(UUID.fromString(uuid));
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        })
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        return new FeedbackListProvider(res);
    }

    @GetMapping("/api/v1/customer/{id}/stats")
    public CustomerStats getCustomerStatsById(@PathVariable("id") String uuid) throws RemoteException {
        var customer = service.getCustomerByUUID(uuid);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return new CustomerStats(uuid, customer);
    }

    @PostMapping("/api/v1/customer")
    public CustomerProvider postCustomer(@Valid @RequestBody CustomerProvider customer) throws RemoteException {
        var uuid = this.service.add(
                customer.firstName,
                customer.lastName,
                CustomerType.valueOf(customer.type),
                customer.username,
                customer.password
        );
        if (uuid == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Customer creation failed");
        }
        var user = this.service.getCustomerByUUID(uuid.toString());
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return new CustomerProvider(uuid, user);
    }

    private RentalListProvider getCustomerRentalsById(String id) throws RemoteException{
        var res = rentService.getAll().entrySet().stream()
        .filter(t -> {
            try {
                return t.getValue().getCustomerClientUUID().equals(UUID.fromString(id));
            } catch (RemoteException e) {
                e.printStackTrace();
                return false;
            }
        })
        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        return new RentalListProvider(res);
    }

    class CustomerStats{
        @JsonProperty(
        value = "num_bike",
        access = JsonProperty.Access.READ_ONLY
        )
        public final int totalBikes;
        
        @JsonProperty(
        value = "num_review",
        access = JsonProperty.Access.READ_ONLY
        )
        public final int totalReviews;
        
        @JsonProperty(
        value = "num_rental",
        access = JsonProperty.Access.READ_ONLY
        )
        public final int totalRentals;

        public CustomerStats(String uuid, CustomerService customer) throws RemoteException {
            this.totalBikes = customer.getBikes().size();
            this.totalReviews = getCustomerFeedbackById(uuid).count;
            this.totalRentals = getCustomerRentalsById(uuid).count;
        }
    }

}

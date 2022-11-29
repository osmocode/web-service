package web.service.rest.providers;

import com.fasterxml.jackson.annotation.JsonProperty;
import rmi.customer.interfaces.CustomerService;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.rmi.RemoteException;
import java.util.*;

public class CustomerListProvider {

    @JsonProperty("count")
    public final int count;

    @JsonProperty("results")
    public final List<CustomerProvider> customers;

    public CustomerListProvider(Map<UUID, ? extends CustomerService> customers) {
        this.count = customers.size();
        this.customers = customers.entrySet().stream().map((entry) -> {
            try {
                return new CustomerProvider(entry.getKey(), entry.getValue());
            } catch (RemoteException e) {
                throw new RuntimeException(e.getCause());
            }
        }).toList();

    }

}

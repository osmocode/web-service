package web.service.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rmi.bike.interfaces.bike.BikeListService;
import rmi.customer.interfaces.CustomerListService;

import java.rmi.RemoteException;
import java.util.HashMap;


@Component
public class SellRepository {


    @Autowired
    private BikeListService bikeService;

    @Autowired
    private CustomerListService authService;


    private final HashMap<String, SellPrice> repo = new HashMap<>();


    public void addToSell(String token, String uuid, String currency, Double price) throws RemoteException {

    }


}

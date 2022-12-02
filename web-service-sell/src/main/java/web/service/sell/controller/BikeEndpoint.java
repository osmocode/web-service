package web.service.sell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.schema.web_services.Bike;
import org.springframework.schema.web_services.GetBikeRequest;
import org.springframework.schema.web_services.GetBikeResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import rmi.bike.interfaces.bike.BikeListService;

import java.rmi.RemoteException;
import java.util.UUID;

@Endpoint
public class BikeEndpoint {

    private static final String URL = "http://www.springframework.org/schema/web-services";

    @Autowired
    private BikeListService bikeService;

    @PayloadRoot(namespace = URL, localPart = "getBikeRequest")
    @ResponsePayload
    public GetBikeResponse getBikeResponse(@RequestPayload GetBikeRequest request) throws RemoteException {
        GetBikeResponse response = new GetBikeResponse();
        var bike = new Bike();
        var b = bikeService.getBikeByUUID("00000000-0000-0000-0000-00000000");
        bike.setId(request.getId());
        bike.setLabel(b.getLabel());
        bike.setDesc(b.getDescription());
        bike.setOwner(b.getOwnerId().toString());
        response.setBike(bike);
        return response;
    }


}

package web.service.sell.controller;

import io.spring.guides.gs_producing_web_service.Bike;
import io.spring.guides.gs_producing_web_service.GetBikeRequest;
import io.spring.guides.gs_producing_web_service.GetBikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import rmi.bike.interfaces.bike.BikeListService;

import java.rmi.RemoteException;

@Endpoint
public class BikeEndpoint {

    private static final String URL = "http://localhost/xml/web-service";

    @Autowired
    private BikeListService bikeService;

    @PayloadRoot(namespace = URL, localPart = "getBikeRequest")
    @ResponsePayload
    public GetBikeResponse getBikeResponse(@RequestPayload GetBikeRequest request) throws RemoteException {
        GetBikeResponse response = new GetBikeResponse();
        var bike = new Bike();
        var b = bikeService.getBikeByUUID(request.getId());
        bike.setId(request.getId());
        bike.setLabel(b.getLabel());
        bike.setDesc(b.getDescription());
        bike.setOwner(b.getOwnerId().toString());
        response.setBike(bike);
        return response;
    }


}

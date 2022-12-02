package web.service.sell.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.schema.web_services.Bike;
import org.springframework.schema.web_services.GetBikeList;
import org.springframework.schema.web_services.GetBikeRequest;
import org.springframework.schema.web_services.GetBikeResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import rmi.bike.interfaces.bike.BikeListService;
import web.service.sell.providers.Providers;

import java.io.UncheckedIOException;
import java.rmi.RemoteException;

@Endpoint
public class BikeEndpoint {

    private static final String NAMESPACE = "http://www.springframework.org/schema/web-services";

    @Autowired
    private BikeListService bikeService;

    @PayloadRoot(namespace = NAMESPACE, localPart = "getBikeRequest")
    @ResponsePayload
    public GetBikeResponse getBikeResponse(@RequestPayload GetBikeRequest request) throws RemoteException {
        GetBikeResponse response = new GetBikeResponse();
        var bike = bikeService.getBikeByUUID(request.getId());
        if ( bike == null) {
            return response;
        }
        response.setBike(Providers.getBike(request.getId(), bike));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getBikeListRequest")
    @ResponsePayload
    public GetBikeList getBikeList() throws RemoteException {
        GetBikeList response = new GetBikeList();
        bikeService.getAll().entrySet().stream().forEach((entry) -> {
            try {
                response.getBike().add(Providers.getBike(entry.getKey().toString(), entry.getValue()));
            } catch (RemoteException e) {
                throw new UncheckedIOException(e);
            }
        });
        return response;
    }



}

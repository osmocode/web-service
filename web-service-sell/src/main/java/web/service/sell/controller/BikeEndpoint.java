package web.service.sell.controller;

import io.spring.guides.gs_producing_web_service.Bike;
import io.spring.guides.gs_producing_web_service.GetBikeRequest;
import io.spring.guides.gs_producing_web_service.GetBikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class BikeEndpoint {

    private static final String URL = "http://localhost/xml/web-service";

    @PayloadRoot(namespace = URL, localPart = "getBikeRequest")
    @ResponsePayload
    public GetBikeResponse getBikeResponse(@RequestPayload GetBikeRequest request) {
        GetBikeResponse response = new GetBikeResponse();
        var bike = new Bike();
        bike.setLabel("Super bike");
        bike.setDesc("Lorem ipsum");
        response.setBike(bike);
        return response;
    }


}

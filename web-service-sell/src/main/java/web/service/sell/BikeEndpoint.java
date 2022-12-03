package web.service.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import rmi.bike.interfaces.bike.BikeListService;
import web.service.wsdl.bike.*;


import java.io.UncheckedIOException;
import java.rmi.RemoteException;


@Endpoint
public class BikeEndpoint {

    private static final String NAMESPACE = "http://www.springframework.org/schema/web-services";

    @Autowired
    private BikeListService bikeService;

    @Autowired
    private Jaxb2Marshaller marshaller;

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
    public GetBikeListResponse getBikeList() throws RemoteException {
        GetBikeListResponse response = new GetBikeListResponse();
        bikeService.getAll().entrySet().stream().forEach((entry) -> {
            try {
                response.getBike().add(Providers.getBike(entry.getKey().toString(), entry.getValue()));
            } catch (RemoteException e) {
                throw new UncheckedIOException(e);
            }
        });
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getConvertRequest")
    @ResponsePayload
    public GetConvertResponse getConvertResponse(@RequestPayload GetConvertRequest request) {
        var template = new WebServiceTemplate(marshaller);
        var response = (web.service.wsdl.convertor.GetConvertResponse) template.marshalSendAndReceive("http://ws-convertor:8080/ws/convertor", Providers.GetConvertRequest(request),
                new SoapActionCallback(NAMESPACE));
        return Providers.GetConvertResponse(response);
    }

}

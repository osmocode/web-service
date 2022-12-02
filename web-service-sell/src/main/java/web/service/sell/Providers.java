package web.service.sell;


import rmi.bike.interfaces.bike.BikeService;
import web.service.wsdl.sell.*;

import java.rmi.RemoteException;

public class Providers {

    public static Bike getBike(String uuid, BikeService bikeService) throws RemoteException {
        var bike = new Bike();
        bike.setId(uuid);
        bike.setLabel(bikeService.getLabel());
        bike.setDesc(bikeService.getDescription());
        bike.setOwner(bikeService.getOwnerId().toString());
        return bike;
    }

    public static web.service.wsdl.convertor.GetConvertRequest GetConvertRequest(GetConvertRequest request) {
        var provider = new web.service.wsdl.convertor.GetConvertRequest();
        provider.setFrom(request.getFrom());
        provider.setTo(request.getTo());
        provider.setAmount(request.getAmount());
        return provider;
    }

    public static GetConvertRequest GetConvertRequest(web.service.wsdl.convertor.GetConvertRequest request) {
        var provider = new GetConvertRequest();
        provider.setFrom(request.getFrom());
        provider.setTo(request.getTo());
        provider.setAmount(request.getAmount());
        return provider;
    }

    public static web.service.wsdl.convertor.GetConvertResponse GetConvertResponse(GetConvertResponse response) {
        var provider = new web.service.wsdl.convertor.GetConvertResponse();
        provider.setResult(response.getResult());
        return provider;
    }

    public static GetConvertResponse GetConvertResponse(web.service.wsdl.convertor.GetConvertResponse response) {
        var provider = new GetConvertResponse();
        provider.setResult(response.getResult());
        return provider;
    }

}

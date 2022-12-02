package web.service.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import web.service.wsdl.convertor.GetConvertRequest;
import web.service.wsdl.convertor.GetConvertResponse;
import web.service.wsdl.convertor.GetCurrencyListResponse;


@Endpoint
public class Controller {

    private static final String NAMESPACE = "http://www.springframework.org/schema/web-services";

    @Autowired
    private Convertor service;

    @PayloadRoot(namespace = NAMESPACE, localPart = "getConvertRequest")
    @ResponsePayload
    public GetConvertResponse getConvertResponse(@RequestPayload GetConvertRequest request) {
        GetConvertResponse response = new GetConvertResponse();
        var result = this.service.getCurrency(request.getFrom(), request.getTo(), request.getAmount());
        response.setResult(result);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE, localPart = "getCurrencyListRequest")
    @ResponsePayload
    public GetCurrencyListResponse getCurrencyListResponse() {
        GetCurrencyListResponse response = new GetCurrencyListResponse();
        this.service.getCurrencyList().forEach(currency -> {
            web.service.wsdl.convertor.Currency c = new web.service.wsdl.convertor.Currency();
            c.setFrom(currency.from());
            c.setTo(currency.to());
            c.setRate(currency.rate());
            response.getResults().add(c);
        });
        return response;
    }

}

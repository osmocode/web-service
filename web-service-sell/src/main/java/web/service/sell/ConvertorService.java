package web.service.sell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import web.service.wsdl.convertor.GetConvertRequest;
import web.service.wsdl.convertor.GetConvertResponse;

@Service
public class ConvertorService {

    @Autowired
    private Jaxb2Marshaller marshaller;

    private WebServiceTemplate template;

    public GetConvertResponse convertCurrency(String from, String to, Double value) {
        template = new WebServiceTemplate();
        var request = new GetConvertRequest();
        request.setFrom(from);
        request.setTo(to);
        request.setAmount(value);
        GetConvertResponse response = (GetConvertResponse) template.marshalSendAndReceive(
                "http://ws-convertor:8080/ws/convertor/convertor",
                request
        );
        return response;
    }

}

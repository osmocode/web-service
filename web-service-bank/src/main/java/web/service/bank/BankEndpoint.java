package web.service.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class BankEndpoint {

    @Autowired
    private BankService bankService;




}
